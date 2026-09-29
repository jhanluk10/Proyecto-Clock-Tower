package servidor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQLite {

    private static volatile boolean schemaVerificado = false;

    private static String obtenerRutaBD() {
        String userDir = System.getProperty("user.dir");
        System.out.println("Carpeta de trabajo: " + userDir);

        String[] rutas = {
            // En la carpeta del proyecto
            userDir + File.separator + "restaurante.db",
            userDir + File.separator + "restaurante",
            // Carpeta Bases De Datos dentro del proyecto
            userDir + File.separator + "Bases De Datos" + File.separator + "restaurante.db",
            // Carpeta hermana (BACKUP.../Bases De Datos)
            userDir + File.separator + ".." + File.separator + "Bases De Datos" + File.separator + "restaurante.db",
            // Relativas
            "restaurante.db",
            "Bases De Datos/restaurante.db",
            "../Bases De Datos/restaurante.db"
        };

        for (String ruta : rutas) {
            File f = new File(ruta);
            System.out.println("Probando: " + f.getAbsolutePath() + " existe=" + f.exists());
            if (f.exists() && f.isFile()) {
                System.out.println(">>> BD ENCONTRADA: " + f.getAbsolutePath());
                return f.getAbsolutePath();
            }
        }

        System.err.println("NO se encontro restaurante.db en ninguna ruta");
        return userDir + File.separator + "restaurante.db";
    }

    public static Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + obtenerRutaBD().replace("\\", "/");
            System.out.println("URL: " + url);
            Connection c = DriverManager.getConnection(url);
            System.out.println("Conexion SQLite OK");
            asegurarEsquema(c);
            return c;
        } catch (ClassNotFoundException e) {
            System.err.println("FALTA el driver SQLite");
            return null;
        } catch (SQLException e) {
            System.err.println("Error SQLite: " + e.getMessage());
            return null;
        }
    }

    /**
     * Asegura que existan las columnas necesarias (pin, bloqueado_hasta, etc.)
     * para bases de datos antiguas. Solo se ejecuta una vez por proceso.
     */
    private static synchronized void asegurarEsquema(Connection c) {
        if (schemaVerificado || c == null) {
            return;
        }
        try (Statement st = c.createStatement()) {
            // Verificar columnas existentes
            boolean tienePin = false;
            boolean tieneBloqueadoHasta = false;
            boolean tieneIntentos = false;
            boolean tieneBloqueado = false;
            boolean tieneActivo = false;

            try (ResultSet rs = st.executeQuery("PRAGMA table_info(usuarios)")) {
                while (rs.next()) {
                    String col = rs.getString("name");
                    if ("pin".equalsIgnoreCase(col)) tienePin = true;
                    if ("bloqueado_hasta".equalsIgnoreCase(col)) tieneBloqueadoHasta = true;
                    if ("intentos_fallidos".equalsIgnoreCase(col)) tieneIntentos = true;
                    if ("bloqueado".equalsIgnoreCase(col)) tieneBloqueado = true;
                    if ("activo".equalsIgnoreCase(col)) tieneActivo = true;
                }
            }

            if (!tienePin) {
                st.execute("ALTER TABLE usuarios ADD COLUMN pin TEXT");
                System.out.println("Columna pin agregada a usuarios");
            }
            if (!tieneIntentos) {
                st.execute("ALTER TABLE usuarios ADD COLUMN intentos_fallidos INTEGER NOT NULL DEFAULT 0");
                System.out.println("Columna intentos_fallidos agregada a usuarios");
            }
            if (!tieneBloqueado) {
                st.execute("ALTER TABLE usuarios ADD COLUMN bloqueado INTEGER NOT NULL DEFAULT 0");
                System.out.println("Columna bloqueado agregada a usuarios");
            }
            if (!tieneActivo) {
                st.execute("ALTER TABLE usuarios ADD COLUMN activo INTEGER NOT NULL DEFAULT 1");
                System.out.println("Columna activo agregada a usuarios");
            }
            if (!tieneBloqueadoHasta) {
                st.execute("ALTER TABLE usuarios ADD COLUMN bloqueado_hasta INTEGER DEFAULT 0");
                System.out.println("Columna bloqueado_hasta agregada a usuarios");
            }

            // Tabla de bitácora de accesos (logins fallidos, bloqueos, cambios de PIN)
            st.execute(
                "CREATE TABLE IF NOT EXISTS bitacora_acceso (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT NOT NULL, " +
                "evento TEXT NOT NULL, " +
                "detalle TEXT, " +
                "fecha TEXT NOT NULL)"
            );

            schemaVerificado = true;
        } catch (SQLException e) {
            System.err.println("Advertencia al verificar esquema: " + e.getMessage());
            // No bloquear la conexión si falla la migración
            schemaVerificado = true;
        }
    }
}
