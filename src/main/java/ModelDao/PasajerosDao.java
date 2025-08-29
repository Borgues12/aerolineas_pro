package dao;

import model.Pasajeros;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajerosDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean insertar(Pasajeros pasajero) {
        String sql = "INSERT INTO pasajero (nombre, cedula) VALUES (?, ?)";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pasajero.getNombre());
            ps.setString(2, pasajero.getCedula());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar pasajero: " + e.getMessage());
            return false;
        }
    }

    public List<Pasajeros> listar() {
        List<Pasajeros> lista = new ArrayList<>();
        String sql = "SELECT * FROM pasajero";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pasajeros p = new Pasajeros();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCedula(rs.getString("cedula"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pasajeros: " + e.getMessage());
        }
        return lista;
    }

    public Pasajeros obtenerPorId(int id) {
        String sql = "SELECT * FROM pasajero WHERE id = ?";
        Pasajeros p = null;
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Pasajeros();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCedula(rs.getString("cedula"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pasajero: " + e.getMessage());
        }
        return p;
    }

    public boolean actualizar(Pasajeros pasajero) {
        String sql = "UPDATE pasajero SET nombre = ?, cedula = ? WHERE id = ?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pasajero.getNombre());
            ps.setString(2, pasajero.getCedula());
            ps.setInt(3, pasajero.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar pasajero: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM pasajero WHERE id = ?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al el iminar pasajero: " + e.getMessage());
            return false;
        }
    }
}
