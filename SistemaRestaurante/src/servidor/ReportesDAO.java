package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reportes simples: ventas del día y productos más vendidos.
 */
public class ReportesDAO {

    /**
     * Ventas del día (fecha local de la BD).
     * Formato respuesta:
     * REPORTES_OK|VENTAS_DIA|totalPedidos|totalItems|montoTotal;;idPedido|mesa|fecha|estado|subtotal;;...
     */
    public static String ventasDelDia() {
        String sqlResumen =
            "SELECT COUNT(DISTINCT p.idPedido) AS totalPedidos, " +
            "COALESCE(SUM(d.cantidad),0) AS totalItems, " +
            "COALESCE(SUM(d.cantidad * d.precioUnitario),0) AS montoTotal " +
            "FROM pedidos p " +
            "LEFT JOIN detalle_pedido d ON d.idPedido = p.idPedido " +
            "WHERE date(p.fecha) = date('now','localtime')";

        String sqlDetalle =
            "SELECT p.idPedido, p.idMesa, p.fecha, p.estado, " +
            "COALESCE(SUM(d.cantidad * d.precioUnitario),0) AS subtotal " +
            "FROM pedidos p " +
            "LEFT JOIN detalle_pedido d ON d.idPedido = p.idPedido " +
            "WHERE date(p.fecha) = date('now','localtime') " +
            "GROUP BY p.idPedido " +
            "ORDER BY p.fecha DESC";

        Connection c = ConexionSQLite.conectar();
        if (c == null) {
            return "REPORTES_ERROR|No se pudo conectar a la base de datos";
        }

        StringBuilder sb = new StringBuilder("REPORTES_OK|VENTAS_DIA|");
        try (c) {
            int totalPedidos = 0;
            int totalItems = 0;
            double montoTotal = 0;

            try (PreparedStatement ps = c.prepareStatement(sqlResumen);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalPedidos = rs.getInt("totalPedidos");
                    totalItems = rs.getInt("totalItems");
                    montoTotal = rs.getDouble("montoTotal");
                }
            }

            sb.append(totalPedidos).append("|")
              .append(totalItems).append("|")
              .append(String.format(java.util.Locale.US, "%.2f", montoTotal));

            try (PreparedStatement ps = c.prepareStatement(sqlDetalle);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sb.append(";;")
                      .append(rs.getInt("idPedido")).append("|")
                      .append(rs.getInt("idMesa")).append("|")
                      .append(rs.getString("fecha")).append("|")
                      .append(rs.getString("estado")).append("|")
                      .append(String.format(java.util.Locale.US, "%.2f", rs.getDouble("subtotal")));
                }
            }
        } catch (SQLException e) {
            return "REPORTES_ERROR|" + e.getMessage();
        }
        return sb.toString();
    }

    /**
     * Productos más vendidos (histórico completo, top N).
     * Formato:
     * REPORTES_OK|TOP_PRODUCTOS|idProducto|nombre|cantidadVendida|monto;;...
     */
    public static String productosMasVendidos(int limite) {
        if (limite <= 0) limite = 10;

        String sql =
            "SELECT pr.idProducto, pr.nombre, " +
            "COALESCE(SUM(d.cantidad),0) AS cantidadVendida, " +
            "COALESCE(SUM(d.cantidad * d.precioUnitario),0) AS monto " +
            "FROM productos pr " +
            "LEFT JOIN detalle_pedido d ON d.idProducto = pr.idProducto " +
            "GROUP BY pr.idProducto " +
            "HAVING cantidadVendida > 0 " +
            "ORDER BY cantidadVendida DESC " +
            "LIMIT ?";

        Connection c = ConexionSQLite.conectar();
        if (c == null) {
            return "REPORTES_ERROR|No se pudo conectar a la base de datos";
        }

        StringBuilder sb = new StringBuilder("REPORTES_OK|TOP_PRODUCTOS");
        try (c; PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                boolean hay = false;
                while (rs.next()) {
                    hay = true;
                    sb.append(";;")
                      .append(rs.getInt("idProducto")).append("|")
                      .append(rs.getString("nombre")).append("|")
                      .append(rs.getInt("cantidadVendida")).append("|")
                      .append(String.format(java.util.Locale.US, "%.2f", rs.getDouble("monto")));
                }
                if (!hay) {
                    // sin ventas
                }
            }
        } catch (SQLException e) {
            return "REPORTES_ERROR|" + e.getMessage();
        }
        return sb.toString();
    }
}
