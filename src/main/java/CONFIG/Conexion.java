package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/aerolinea_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "12345678*";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver cargado exitosamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: MySQL JDBC Driver no encontrado. " + e.getMessage());
            throw new RuntimeException("No se pudo cargar el driver JDBC de MySQL", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("❌ Error al establecer la conexión a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    public static void closeResources(Connection connection, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try { rs.close(); } catch (SQLException e) {}
        }
        if (ps != null) {
            try { ps.close(); } catch (SQLException e) {}
        }
        if (connection != null) {
            try { connection.close(); } catch (SQLException e) {}
        }
    }
}
