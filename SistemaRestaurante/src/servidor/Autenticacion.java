package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticacion {

    private static final int MAX_INTENTOS = 5;
    private static final int SEGUNDOS_BLOQUEO = 60; // 1 minuto

    public static String validarUsuario(String usuario, String contrasena) {

        // Datos leídos de la BD (se leen primero y se cierra la conexión)
        String pass = null;
        String rol = null;
        int bloqueado = 0;
        int activo = 1;
        String pin = null;
        int intentos = 0;
        long bloqueadoHasta = 0;

        Connection conexion = ConexionSQLite.conectar();
        if (conexion == null) {
            return "LOGIN_ERROR|No se pudo conectar a la base de datos";
        }

        String sql = "SELECT usuario, contrasena, rol, bloqueado, activo, pin, "
                   + "intentos_fallidos, bloqueado_hasta FROM usuarios WHERE usuario = ?";

        try (conexion;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    registrarEventoBitacora(usuario, "LOGIN_FALLIDO", "Usuario no encontrado");
                    return "LOGIN_ERROR|Usuario no encontrado";
                }
                pass = rs.getString("contrasena");
                rol = rs.getString("rol");
                bloqueado = rs.getInt("bloqueado");
                activo = rs.getInt("activo");
                pin = rs.getString("pin");
                intentos = rs.getInt("intentos_fallidos");
                bloqueadoHasta = rs.getLong("bloqueado_hasta");
            }
        } catch (SQLException e) {
            return "LOGIN_ERROR|Error BD: " + e.getMessage();
        }

        // A partir de aquí la conexión de lectura ya está cerrada
        long ahora = System.currentTimeMillis() / 1000L;

        // Bloqueo temporal vigente
        if (bloqueado == 1 && bloqueadoHasta > 0) {
            if (ahora < bloqueadoHasta) {
                long segundosRestantes = bloqueadoHasta - ahora;
                return "LOGIN_ERROR|⚠ SESIÓN BLOQUEADA\nEspere " + segundosRestantes + " segundo(s) antes de reintentar.";
            } else {
                // Tiempo expirado: desbloquear
                resetearIntentos(usuario);
                intentos = 0;
                bloqueado = 0;
            }
        } else if (bloqueado == 1) {
            return "LOGIN_ERROR|Usuario bloqueado";
        }

        if (activo == 0) {
            return "LOGIN_ERROR|Usuario inactivo";
        }

        // Contraseña incorrecta
        if (pass == null || !pass.equals(contrasena)) {
            intentos++;
            if (intentos >= MAX_INTENTOS) {
                long hasta = ahora + SEGUNDOS_BLOQUEO;
                registrarBloqueo(usuario, intentos, hasta);
                return "LOGIN_ERROR|⚠ SESIÓN BLOQUEADA\nDemasiados intentos fallidos.\nEspere 60 segundos antes de reintentar.";
            } else {
                actualizarIntentos(usuario, intentos);
                int restantes = MAX_INTENTOS - intentos;
                return "LOGIN_ERROR|Contraseña incorrecta. Intentos restantes: " + restantes;
            }
        }

        // Login correcto: resetear intentos si había fallos previos
        if (intentos > 0 || bloqueado == 1) {
            resetearIntentos(usuario);
        }

        boolean tienePin = (pin != null && !pin.trim().isEmpty());
        String estadoPin = tienePin ? "HAS_PIN" : "NO_PIN";

        registrarEventoBitacora(usuario, "LOGIN_OK", "Inicio de sesión exitoso. Rol: " + rol);
        return "LOGIN_OK|" + rol + "|" + estadoPin;
    }

    /**
     * Configura el PIN del usuario administrador.
     */
    /**
     * Valida formato de PIN: solo dígitos, 4 a 6 caracteres.
     */
    public static String validarFormatoPin(String pin) {
        if (pin == null || pin.trim().isEmpty()) {
            return "El PIN no puede estar vacío";
        }
        String p = pin.trim();
        if (p.length() < 4 || p.length() > 6) {
            return "El PIN debe tener entre 4 y 6 dígitos";
        }
        if (!p.matches("\\d+")) {
            return "El PIN debe contener solo números";
        }
        return null; // válido
    }

    public static String configurarPin(String usuario, String pin) {
        String errorFormato = validarFormatoPin(pin);
        if (errorFormato != null) {
            return "PIN_ERROR|" + errorFormato;
        }

        String sql = "UPDATE usuarios SET pin = ? WHERE usuario = ? AND rol = 'admin'";

        Connection conexion = ConexionSQLite.conectar();
        if (conexion == null) {
            return "PIN_ERROR|No se pudo conectar a la base de datos";
        }

        try (conexion;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pin.trim());
            ps.setString(2, usuario);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return "PIN_OK|PIN configurado correctamente";
            }
            return "PIN_ERROR|No se pudo configurar el PIN (usuario no es admin o no existe)";
        } catch (SQLException e) {
            return "PIN_ERROR|Error BD: " + e.getMessage();
        }
    }

    /**
     * Verifica el PIN del usuario.
     * Los intentos fallidos de PIN también cuentan hacia el bloqueo temporal.
     */
    public static String verificarPin(String usuario, String pinIngresado) {
        if (pinIngresado == null || pinIngresado.trim().isEmpty()) {
            return "PIN_ERROR|Debe ingresar el PIN";
        }

        String pinGuardado = null;
        int intentos = 0;
        int bloqueado = 0;
        long bloqueadoHasta = 0;

        String sql = "SELECT pin, intentos_fallidos, bloqueado, bloqueado_hasta FROM usuarios WHERE usuario = ?";

        Connection conexion = ConexionSQLite.conectar();
        if (conexion == null) {
            return "PIN_ERROR|No se pudo conectar a la base de datos";
        }

        try (conexion;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return "PIN_ERROR|Usuario no encontrado";
                }
                pinGuardado = rs.getString("pin");
                intentos = rs.getInt("intentos_fallidos");
                bloqueado = rs.getInt("bloqueado");
                bloqueadoHasta = rs.getLong("bloqueado_hasta");
            }
        } catch (SQLException e) {
            return "PIN_ERROR|Error BD: " + e.getMessage();
        }

        long ahora = System.currentTimeMillis() / 1000L;

        // Verificar bloqueo temporal
        if (bloqueado == 1 && bloqueadoHasta > 0) {
            if (ahora < bloqueadoHasta) {
                long segundosRestantes = bloqueadoHasta - ahora;
                return "PIN_ERROR|⚠ SESIÓN BLOQUEADA\nEspere " + segundosRestantes + " segundo(s) antes de reintentar.";
            } else {
                resetearIntentos(usuario);
                intentos = 0;
            }
        }

        if (pinGuardado == null || pinGuardado.trim().isEmpty()) {
            return "PIN_ERROR|El usuario no tiene PIN configurado";
        }

        if (pinGuardado.equals(pinIngresado.trim())) {
            if (intentos > 0) {
                resetearIntentos(usuario);
            }
            return "PIN_OK|PIN correcto";
        }

        // PIN incorrecto
        intentos++;
        if (intentos >= MAX_INTENTOS) {
            long hasta = ahora + SEGUNDOS_BLOQUEO;
            registrarBloqueo(usuario, intentos, hasta);
            return "PIN_ERROR|⚠ SESIÓN BLOQUEADA\nDemasiados intentos fallidos.\nEspere 60 segundos antes de reintentar.";
        } else {
            actualizarIntentos(usuario, intentos);
            int restantes = MAX_INTENTOS - intentos;
            return "PIN_ERROR|PIN incorrecto. Intentos restantes: " + restantes;
        }
    }

    /**
     * Cambia el PIN del administrador verificando el PIN actual.
     * Formato: CHANGE_PIN|usuario|pinActual|pinNuevo
     */
    public static String cambiarPin(String usuario, String pinActual, String pinNuevo) {
        String errorFormato = validarFormatoPin(pinNuevo);
        if (errorFormato != null) {
            return "PIN_ERROR|" + errorFormato;
        }
        if (pinActual == null || pinActual.trim().isEmpty()) {
            return "PIN_ERROR|Debe ingresar el PIN actual";
        }

        String pinGuardado = null;
        String sqlSel = "SELECT pin FROM usuarios WHERE usuario = ? AND rol = 'admin'";
        Connection conexion = ConexionSQLite.conectar();
        if (conexion == null) {
            return "PIN_ERROR|No se pudo conectar a la base de datos";
        }

        try (conexion;
             PreparedStatement ps = conexion.prepareStatement(sqlSel)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return "PIN_ERROR|Usuario no encontrado o no es administrador";
                }
                pinGuardado = rs.getString("pin");
            }
        } catch (SQLException e) {
            return "PIN_ERROR|Error BD: " + e.getMessage();
        }

        if (pinGuardado == null || pinGuardado.isEmpty()) {
            return "PIN_ERROR|No hay PIN configurado. Use la configuración inicial.";
        }
        if (!pinGuardado.equals(pinActual.trim())) {
            registrarEventoBitacora(usuario, "CAMBIO_PIN_FALLIDO", "PIN actual incorrecto");
            return "PIN_ERROR|El PIN actual es incorrecto";
        }
        if (pinActual.trim().equals(pinNuevo.trim())) {
            return "PIN_ERROR|El nuevo PIN debe ser diferente al actual";
        }

        String sqlUpd = "UPDATE usuarios SET pin = ? WHERE usuario = ? AND rol = 'admin'";
        Connection c2 = ConexionSQLite.conectar();
        if (c2 == null) {
            return "PIN_ERROR|No se pudo conectar a la base de datos";
        }
        try (c2; PreparedStatement ps = c2.prepareStatement(sqlUpd)) {
            ps.setString(1, pinNuevo.trim());
            ps.setString(2, usuario);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                registrarEventoBitacora(usuario, "CAMBIO_PIN", "PIN cambiado exitosamente");
                return "PIN_OK|PIN actualizado correctamente";
            }
            return "PIN_ERROR|No se pudo actualizar el PIN";
        } catch (SQLException e) {
            return "PIN_ERROR|Error BD: " + e.getMessage();
        }
    }

    /**
     * Registra un evento en la bitácora de acceso (logins fallidos, bloqueos, etc.).
     */
    public static void registrarEventoBitacora(String usuario, String evento, String detalle) {
        String sql = "INSERT INTO bitacora_acceso (usuario, evento, detalle, fecha) VALUES (?, ?, ?, datetime('now','localtime'))";
        Connection c = ConexionSQLite.conectar();
        if (c == null) return;
        try (c; PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, usuario != null ? usuario : "(desconocido)");
            ps.setString(2, evento);
            ps.setString(3, detalle);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error registrando bitácora: " + e.getMessage());
        }
    }

    /**
     * Lista los últimos eventos de la bitácora (para admin).
     */
    public static String listarBitacora(int limite) {
        if (limite <= 0) limite = 50;
        StringBuilder sb = new StringBuilder("BITACORA_OK|");
        String sql = "SELECT id, usuario, evento, detalle, fecha FROM bitacora_acceso ORDER BY id DESC LIMIT ?";
        Connection c = ConexionSQLite.conectar();
        if (c == null) return "BITACORA_ERROR|No se pudo conectar";
        try (c; PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                boolean primero = true;
                while (rs.next()) {
                    if (!primero) sb.append(";;");
                    primero = false;
                    sb.append(rs.getInt("id")).append("|")
                      .append(rs.getString("usuario")).append("|")
                      .append(rs.getString("evento")).append("|")
                      .append(rs.getString("detalle") != null ? rs.getString("detalle") : "").append("|")
                      .append(rs.getString("fecha"));
                }
            }
        } catch (SQLException e) {
            return "BITACORA_ERROR|" + e.getMessage();
        }
        return sb.toString();
    }

    // --- Métodos auxiliares de bloqueo / intentos ---

    private static void actualizarIntentos(String usuario, int intentos) {
        String sql = "UPDATE usuarios SET intentos_fallidos = ? WHERE usuario = ?";
        Connection c = ConexionSQLite.conectar();
        if (c == null) return;
        try (c; PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, intentos);
            ps.setString(2, usuario);
            ps.executeUpdate();
            registrarEventoBitacora(usuario, "LOGIN_FALLIDO", "Intento fallido #" + intentos);
        } catch (SQLException e) {
            System.err.println("Error actualizando intentos: " + e.getMessage());
        }
    }

    private static void registrarBloqueo(String usuario, int intentos, long hastaUnix) {
        String sql = "UPDATE usuarios SET intentos_fallidos = ?, bloqueado = 1, bloqueado_hasta = ? WHERE usuario = ?";
        Connection c = ConexionSQLite.conectar();
        if (c == null) return;
        try (c; PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, intentos);
            ps.setLong(2, hastaUnix);
            ps.setString(3, usuario);
            ps.executeUpdate();
            registrarEventoBitacora(usuario, "BLOQUEO", "Usuario bloqueado por " + SEGUNDOS_BLOQUEO + "s tras " + intentos + " intentos");
        } catch (SQLException e) {
            System.err.println("Error registrando bloqueo: " + e.getMessage());
        }
    }

    private static void resetearIntentos(String usuario) {
        String sql = "UPDATE usuarios SET intentos_fallidos = 0, bloqueado = 0, bloqueado_hasta = 0 WHERE usuario = ?";
        Connection c = ConexionSQLite.conectar();
        if (c == null) return;
        try (c; PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error reseteando intentos: " + e.getMessage());
        }
    }
}
