package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestionUsuarios {

    public static String agregarUsuario(
            String usuario,
            String contrasena,
            String rol) {

        String sql = "INSERT INTO usuarios "
                   + "(usuario, contrasena, rol) "
                   + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, usuario);
            sentencia.setString(2, contrasena);
            sentencia.setString(3, rol);

            sentencia.executeUpdate();

            return "USUARIO_OK|Usuario creado correctamente";

        } catch (SQLException e) {

            System.err.println("Error al agregar usuario.");
            System.err.println("Error: " + e.getMessage());

            if (e.getMessage().contains("UNIQUE")) {
                return "USUARIO_ERROR|El usuario ya existe";
            }

            return "USUARIO_ERROR|No se pudo crear el usuario";
        }
    }
    public static String desactivarUsuario(String usuario) {

    String sql = "UPDATE usuarios SET activo = 0 WHERE usuario = ?";

    try (Connection conexion = ConexionSQLite.conectar();
         PreparedStatement sentencia = conexion.prepareStatement(sql)) {

        sentencia.setString(1, usuario);

        int filas = sentencia.executeUpdate();

        if (filas > 0) {
            return "USUARIO_OK|Usuario desactivado correctamente";
        }

        return "USUARIO_ERROR|El usuario no existe";

    } catch (SQLException e) {

        System.err.println("Error al desactivar usuario.");
        System.err.println("Error: " + e.getMessage());

        return "USUARIO_ERROR|No se pudo desactivar el usuario";
    }
}
    public static String listarUsuarios() {

    String sql = "SELECT id, usuario, rol, activo "
               + "FROM usuarios "
               + "ORDER BY id";

    StringBuilder resultado = new StringBuilder("USUARIOS_OK|");

    try (Connection conexion = ConexionSQLite.conectar();
         PreparedStatement sentencia = conexion.prepareStatement(sql);
         java.sql.ResultSet resultadoSQL = sentencia.executeQuery()) {

        while (resultadoSQL.next()) {

            int id = resultadoSQL.getInt("id");
            String usuario = resultadoSQL.getString("usuario");
            String rol = resultadoSQL.getString("rol");
            int activo = resultadoSQL.getInt("activo");

            resultado.append(id).append("|");
            resultado.append(usuario).append("|");
            resultado.append(rol).append("|");
            resultado.append(activo).append(";");

        }

        return resultado.toString();

    } catch (SQLException e) {

        System.err.println("Error al listar usuarios.");
        System.err.println("Error: " + e.getMessage());

        return "USUARIOS_ERROR|No se pudieron consultar los usuarios";
    }
}
}