package Config;

import java.sql.*;

public class Conexion {
    Connection con;
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aerolinea_db?useSSL=false", "root", "jose.2007*");
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.toString());
        }
    }
    public Connection getConnection() {
        return con;
    }

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
