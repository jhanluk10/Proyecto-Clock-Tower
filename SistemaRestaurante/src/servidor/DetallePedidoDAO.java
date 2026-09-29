package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetallePedidoDAO {

    public static String agregarDetalle(
            int idPedido,
            int idProducto,
            int cantidad,
            double precioUnitario) {

        String sql = "INSERT INTO detalle_pedido "
                   + "(idPedido, idProducto, cantidad, precioUnitario) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPedido);
            sentencia.setInt(2, idProducto);
            sentencia.setInt(3, cantidad);
            sentencia.setDouble(4, precioUnitario);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                return "DETALLE_OK|Producto agregado al pedido.";
            }

            return "DETALLE_ERROR|No se pudo agregar el producto.";

        } catch (SQLException e) {

            System.err.println(
                    "Error al agregar detalle del pedido: "
                    + e.getMessage()
            );

            return "DETALLE_ERROR|Error de base de datos.";
        }
    }
}