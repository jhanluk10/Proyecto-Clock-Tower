package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO {

    public static String crearPedido(int idMesa) {

        String verificarMesa =
                "SELECT estado FROM mesas WHERE idMesa = ?";

        String crearPedido =
                "INSERT INTO pedidos (idMesa) VALUES (?)";

        String ocuparMesa =
                "UPDATE mesas SET estado = 'Ocupada' WHERE idMesa = ?";

        try (Connection conexion = ConexionSQLite.conectar()) {

            if (conexion == null) {
                return "PEDIDO_ERROR|No se pudo conectar con la base de datos.";
            }

            // 1. VERIFICAR ESTADO DE LA MESA
            try (PreparedStatement sentencia =
                    conexion.prepareStatement(verificarMesa)) {

                sentencia.setInt(1, idMesa);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (!resultado.next()) {
                        return "PEDIDO_ERROR|La mesa no existe.";
                    }

                    String estado = resultado.getString("estado");

                    if (!estado.equalsIgnoreCase("Libre")) {
                        return "PEDIDO_ERROR|La mesa está ocupada.";
                    }
                }
            }

            // 2. CREAR EL PEDIDO
            int idPedido;

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(
                            crearPedido,
                            java.sql.Statement.RETURN_GENERATED_KEYS)) {

                sentencia.setInt(1, idMesa);

                int filas = sentencia.executeUpdate();

                if (filas == 0) {
                    return "PEDIDO_ERROR|No se pudo crear el pedido.";
                }

                try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                    if (!resultado.next()) {
                        return "PEDIDO_ERROR|No se pudo obtener el ID del pedido.";
                    }

                    idPedido = resultado.getInt(1);
                }
            }

            // 3. CAMBIAR MESA A OCUPADA
            try (PreparedStatement sentencia =
                    conexion.prepareStatement(ocuparMesa)) {

                sentencia.setInt(1, idMesa);
                sentencia.executeUpdate();
            }

            System.out.println("Pedido creado correctamente.");
            System.out.println("ID pedido: " + idPedido + " | Mesa: " + idMesa);

            return "PEDIDO_OK|" + idPedido;

        } catch (SQLException e) {
            System.err.println("Error al crear el pedido: " + e.getMessage());
            return "PEDIDO_ERROR|Error de base de datos.";
        }
    }

    public static String cerrarPedido(int idPedido) {

        String buscarPedido =
                "SELECT idMesa, estado FROM pedidos WHERE idPedido = ?";

        String cerrarPedido =
                "UPDATE pedidos SET estado = 'Entregado' WHERE idPedido = ?";

        String liberarMesa =
                "UPDATE mesas SET estado = 'Libre' WHERE idMesa = ?";

        try (Connection conexion = ConexionSQLite.conectar()) {

            if (conexion == null) {
                return "PEDIDO_ERROR|No se pudo conectar con la base de datos.";
            }

            int idMesa;
            String estadoPedido;

            // 1. BUSCAR PEDIDO
            try (PreparedStatement sentencia =
                    conexion.prepareStatement(buscarPedido)) {

                sentencia.setInt(1, idPedido);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (!resultado.next()) {
                        return "PEDIDO_ERROR|El pedido no existe.";
                    }

                    idMesa = resultado.getInt("idMesa");
                    estadoPedido = resultado.getString("estado");
                }
            }

            // 2. VERIFICAR ESTADO DEL PEDIDO
            if (estadoPedido.equalsIgnoreCase("Entregado")) {
                return "PEDIDO_ERROR|El pedido ya fue entregado.";
            }

            // 3. CAMBIAR PEDIDO A ENTREGADO
            try (PreparedStatement sentencia =
                    conexion.prepareStatement(cerrarPedido)) {

                sentencia.setInt(1, idPedido);

                int filas = sentencia.executeUpdate();

                if (filas == 0) {
                    return "PEDIDO_ERROR|No se pudo cerrar el pedido.";
                }
            }

            // 4. LIBERAR MESA
            try (PreparedStatement sentencia =
                    conexion.prepareStatement(liberarMesa)) {

                sentencia.setInt(1, idMesa);

                int filas = sentencia.executeUpdate();

                if (filas == 0) {
                    return "PEDIDO_ERROR|No se pudo liberar la mesa.";
                }
            }

            System.out.println("Pedido cerrado correctamente.");
            System.out.println("ID pedido: " + idPedido + " | Mesa liberada: " + idMesa);

            return "PEDIDO_CERRADO_OK|" + idPedido + "|" + idMesa;

        } catch (SQLException e) {
            System.err.println("Error al cerrar el pedido: " + e.getMessage());
            return "PEDIDO_ERROR|Error de base de datos.";
        }
    }

    /** Cancela un pedido y libera la mesa */
    public static String cancelarPedido(int idPedido) {

        String buscarPedido =
                "SELECT idMesa, estado FROM pedidos WHERE idPedido = ?";

        String liberarMesa =
                "UPDATE mesas SET estado = 'Libre' WHERE idMesa = ?";

        String eliminarDetalles =
                "DELETE FROM detalle_pedido WHERE idPedido = ?";

        String eliminarPedido =
                "DELETE FROM pedidos WHERE idPedido = ?";

        try (Connection conexion = ConexionSQLite.conectar()) {

            if (conexion == null) {
                return "PEDIDO_ERROR|No se pudo conectar con la base de datos.";
            }

            int idMesa;

            try (PreparedStatement ps = conexion.prepareStatement(buscarPedido)) {
                ps.setInt(1, idPedido);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return "PEDIDO_ERROR|El pedido no existe.";
                    }
                    idMesa = rs.getInt("idMesa");
                }
            }

            // Borrar detalles si existen
            try (PreparedStatement ps = conexion.prepareStatement(eliminarDetalles)) {
                ps.setInt(1, idPedido);
                ps.executeUpdate();
            }

            // Borrar pedido
            try (PreparedStatement ps = conexion.prepareStatement(eliminarPedido)) {
                ps.setInt(1, idPedido);
                ps.executeUpdate();
            }

            // Liberar mesa
            try (PreparedStatement ps = conexion.prepareStatement(liberarMesa)) {
                ps.setInt(1, idMesa);
                ps.executeUpdate();
            }

            System.out.println("Pedido cancelado. Mesa liberada: " + idMesa);
            return "PEDIDO_CANCELADO_OK|" + idPedido + "|" + idMesa;

        } catch (SQLException e) {
            System.err.println("Error al cancelar pedido: " + e.getMessage());
            return "PEDIDO_ERROR|Error de base de datos.";
        }
    }
}