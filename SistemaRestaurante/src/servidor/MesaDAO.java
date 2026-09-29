package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MesaDAO {

    public static void listarMesas() {

        String sql = "SELECT idMesa, numero, estado FROM mesas ORDER BY numero";

        try (Connection conexion = ConexionSQLite.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("========== MESAS ==========");

            while (rs.next()) {

                int idMesa = rs.getInt("idMesa");
                int numero = rs.getInt("numero");
                String estado = rs.getString("estado");

                System.out.println(
                        "ID: " + idMesa
                        + " | Mesa: " + numero
                        + " | Estado: " + estado
                );
            }

            System.out.println("===========================");

        } catch (SQLException e) {

            System.err.println("Error al consultar las mesas: "
                    + e.getMessage());
        }
    }
  public static String obtenerMesas() {

    String sql = "SELECT numero, estado FROM mesas ORDER BY numero";

    StringBuilder respuesta = new StringBuilder();

    try (
        Connection conexion = ConexionSQLite.conectar();
        PreparedStatement stmt = conexion.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()
    ) {

        while (rs.next()) {

            int numero = rs.getInt("numero");
            String estado = rs.getString("estado");

            respuesta.append(numero)
                .append("|")
                .append(estado)
                .append(";");
        }

        return respuesta.toString();

    } catch (SQLException e) {

        System.err.println(
                "Error al obtener las mesas: "
                + e.getMessage()
        );

        return "ERROR|No se pudieron obtener las mesas.";
    }
}
        public static void main(String[] args) {
        listarMesas();
        }
}