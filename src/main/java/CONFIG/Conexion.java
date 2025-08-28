package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/// FDVFDVD
public class Conexion {
    // Detalles de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/aerolinea_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASS = "jose.2007*";     // Contraseña de MySQL (coloca la tuya)

    // Bloque estático para cargar el driver JDBC una sola vez
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver cargado exitosamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: MySQL JDBC Driver no encontrado. " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el driver JDBC de MySQL", e);
        }
    }

    // Método estático para obtener una nueva conexión a la base de datos
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            // System.out.println("🔗 Conexión a la base de datos establecida: " + connection);
        } catch (SQLException e) {
            System.err.println("❌ Error al establecer la conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // Método auxiliar estático para cerrar recursos
    public static void closeResources(Connection connection, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try { rs.close(); }
            catch (SQLException e) { System.err.println("❌ Error al cerrar ResultSet: " + e.getMessage()); }
        }
        if (ps != null) {
            try { ps.close(); }
            catch (SQLException e) { System.err.println("❌ Error al cerrar PreparedStatement: " + e.getMessage()); }
        }
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    // System.out.println("🔌 Conexión cerrada correctamente.");
                }
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
