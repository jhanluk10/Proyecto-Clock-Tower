package db;

import modelo.CuerpoDeAgua;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada del manejo de la base de datos SQLite.
 * Implementa las operaciones CRUD.
 */
public class Database {

    private static final String URL = "jdbc:sqlite:cuerpos_agua.db";

    public Database() {
        crearTabla();
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    private void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS cuerpo_agua ("
                + "id INTEGER PRIMARY KEY,"
                + "nombre TEXT NOT NULL,"
                + "municipio TEXT NOT NULL,"
                + "tipo_cuerpo_agua TEXT NOT NULL,"
                + "tipo_agua TEXT NOT NULL,"
                + "irca REAL NOT NULL"
                + ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    /** CREATE */
    public boolean insertar(CuerpoDeAgua cuerpo) {
        String sql = "INSERT INTO cuerpo_agua(id, nombre, municipio, tipo_cuerpo_agua, tipo_agua, irca) VALUES(?,?,?,?,?,?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cuerpo.getId());
            pstmt.setString(2, cuerpo.getNombre());
            pstmt.setString(3, cuerpo.getMunicipio());
            pstmt.setString(4, cuerpo.getTipoCuerpoAgua());
            pstmt.setString(5, cuerpo.getTipoAgua());
            pstmt.setDouble(6, cuerpo.getIrca());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    /** READ - todos */
    public List<CuerpoDeAgua> obtenerTodos() {
        List<CuerpoDeAgua> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuerpo_agua ORDER BY id";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CuerpoDeAgua c = new CuerpoDeAgua(
                        rs.getString("nombre"),
                        rs.getInt("id"),
                        rs.getString("municipio"),
                        rs.getString("tipo_cuerpo_agua"),
                        rs.getString("tipo_agua"),
                        rs.getDouble("irca")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos: " + e.getMessage());
        }
        return lista;
    }

    /** READ - por ID */
    public CuerpoDeAgua buscarPorId(int id) {
        String sql = "SELECT * FROM cuerpo_agua WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new CuerpoDeAgua(
                        rs.getString("nombre"),
                        rs.getInt("id"),
                        rs.getString("municipio"),
                        rs.getString("tipo_cuerpo_agua"),
                        rs.getString("tipo_agua"),
                        rs.getDouble("irca")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por ID: " + e.getMessage());
        }
        return null;
    }

    /** UPDATE */
    public boolean actualizar(CuerpoDeAgua cuerpo) {
        String sql = "UPDATE cuerpo_agua SET nombre=?, municipio=?, tipo_cuerpo_agua=?, tipo_agua=?, irca=? WHERE id=?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cuerpo.getNombre());
            pstmt.setString(2, cuerpo.getMunicipio());
            pstmt.setString(3, cuerpo.getTipoCuerpoAgua());
            pstmt.setString(4, cuerpo.getTipoAgua());
            pstmt.setDouble(5, cuerpo.getIrca());
            pstmt.setInt(6, cuerpo.getId());

            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    /** DELETE */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cuerpo_agua WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    /** Verifica si un ID ya existe */
    public boolean existeId(int id) {
        String sql = "SELECT 1 FROM cuerpo_agua WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
