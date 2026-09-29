package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    public static String obtenerProductos() {

        String sql = "SELECT idProducto, nombre, precio, categoria "
                   + "FROM productos "
                   + "WHERE disponible = 1 "
                   + "ORDER BY categoria, nombre";

        StringBuilder respuesta = new StringBuilder();

        try (
            Connection conexion = ConexionSQLite.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                int idProducto = resultado.getInt("idProducto");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                String categoria = resultado.getString("categoria");

                respuesta.append(idProducto)
                         .append("|")
                         .append(nombre)
                         .append("|")
                         .append(precio)
                         .append("|")
                         .append(categoria)
                         .append(";");
            }

            return respuesta.toString();

        } catch (SQLException e) {

            System.err.println(
                    "Error al obtener los productos: "
                    + e.getMessage()
            );

            return "ERROR|No se pudieron obtener los productos.";
        }
    }
        // ==========================================
    // CREAR PRODUCTO
    // ==========================================

    public static String crearProducto(
            String nombre,
            String descripcion,
            double precio,
            String categoria) {

        String sql =
                "INSERT INTO productos "
                + "(nombre, descripcion, precio, categoria, disponible) "
                + "VALUES (?, ?, ?, ?, 1)";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, descripcion);
            sentencia.setDouble(3, precio);
            sentencia.setString(4, categoria);

            int filas = sentencia.executeUpdate();

            if (filas == 0) {
                return "PRODUCTO_ERROR|No se pudo crear el producto.";
            }

            return "PRODUCTO_OK|Producto creado correctamente.";

        } catch (SQLException e) {

            System.err.println(
                    "Error al crear producto: "
                    + e.getMessage()
            );

            return "PRODUCTO_ERROR|Error de base de datos.";
        }
    }


    // ==========================================
    // EDITAR PRODUCTO
    // ==========================================

    public static String editarProducto(
            int idProducto,
            String nombre,
            String descripcion,
            double precio,
            String categoria) {

        String sql =
                "UPDATE productos SET "
                + "nombre = ?, "
                + "descripcion = ?, "
                + "precio = ?, "
                + "categoria = ? "
                + "WHERE idProducto = ?";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, descripcion);
            sentencia.setDouble(3, precio);
            sentencia.setString(4, categoria);
            sentencia.setInt(5, idProducto);

            int filas = sentencia.executeUpdate();

            if (filas == 0) {
                return "PRODUCTO_ERROR|El producto no existe.";
            }

            return "PRODUCTO_OK|Producto actualizado correctamente.";

        } catch (SQLException e) {

            System.err.println(
                    "Error al editar producto: "
                    + e.getMessage()
            );

            return "PRODUCTO_ERROR|Error de base de datos.";
        }
    }


    // ==========================================
    // CAMBIAR DISPONIBILIDAD
    // ==========================================

    public static String cambiarDisponibilidad(
            int idProducto,
            int disponible) {

        String sql =
                "UPDATE productos SET disponible = ? "
                + "WHERE idProducto = ?";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, disponible);
            sentencia.setInt(2, idProducto);

            int filas = sentencia.executeUpdate();

            if (filas == 0) {
                return "PRODUCTO_ERROR|El producto no existe.";
            }

            return "PRODUCTO_OK|Disponibilidad actualizada.";

        } catch (SQLException e) {

            System.err.println(
                    "Error al cambiar disponibilidad: "
                    + e.getMessage()
            );

            return "PRODUCTO_ERROR|Error de base de datos.";
        }
    }


    // ==========================================
    // ELIMINAR PRODUCTO
    // ==========================================

    public static String eliminarProducto(int idProducto) {

        String sql =
                "DELETE FROM productos "
                + "WHERE idProducto = ?";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idProducto);

            int filas = sentencia.executeUpdate();

            if (filas == 0) {
                return "PRODUCTO_ERROR|El producto no existe.";
            }

            return "PRODUCTO_OK|Producto eliminado correctamente.";

        } catch (SQLException e) {

            System.err.println(
                    "Error al eliminar producto: "
                    + e.getMessage()
            );

            return "PRODUCTO_ERROR|No se pudo eliminar el producto.";
        }
    }
    public static String obtenerTodosLosProductos() {

    String sql =
            "SELECT idProducto, nombre, descripcion, precio, "
            + "categoria, disponible "
            + "FROM productos "
            + "ORDER BY categoria, nombre";

    StringBuilder respuesta =
            new StringBuilder();

    try (
        Connection conexion = ConexionSQLite.conectar();
        PreparedStatement sentencia =
                conexion.prepareStatement(sql);
        ResultSet resultado =
                sentencia.executeQuery()
    ) {

        while (resultado.next()) {

            int idProducto =
                    resultado.getInt("idProducto");

            String nombre =
                    resultado.getString("nombre");

            String descripcion =
                    resultado.getString("descripcion");

            double precio =
                    resultado.getDouble("precio");

            String categoria =
                    resultado.getString("categoria");

            int disponible =
                    resultado.getInt("disponible");

            respuesta.append(idProducto)
                     .append("|")
                     .append(nombre)
                     .append("|")
                     .append(descripcion == null ? "" : descripcion)
                     .append("|")
                     .append(precio)
                     .append("|")
                     .append(categoria)
                     .append("|")
                     .append(disponible)
                     .append(";");
        }

        return respuesta.toString();

    } catch (SQLException e) {

        System.err.println(
                "Error al obtener todos los productos: "
                + e.getMessage()
        );

        return "ERROR|No se pudieron obtener los productos.";
    }
}
}