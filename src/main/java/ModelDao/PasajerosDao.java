package ModelDao;

import Config.Conexion;
import Interfaces.IntPasajeros;
import MODEL.Aviones;
import MODEL.Pasajeros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PasajerosDao implements IntPasajeros {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<Pasajeros> todosLosPasajeros() {
        List<Pasajeros> lista_pasajeros = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT * FROM pasajero"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Pasajeros p = new Pasajeros();
                p.setId_pasajero(rs.getInt("id_pasajero"));
                p.setNombre_pasajero(rs.getString("nombre_pasajero"));
                p.setCedula(rs.getString("cedula"));
                p.setEstado_pasajero(rs.getString("estado_pasajero"));
                lista_pasajeros.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar pasajeros: " + e.getMessage());
        }
        return lista_pasajeros;
    }

    @Override
    public Pasajeros obtenerPasajeroPorCedula(String cedula) {
        Pasajeros p = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT * FROM pasajero WHERE cedula = ?"
            );
            ps.setString(1,cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Pasajeros();
                p.setId_pasajero(rs.getInt("id_pasajero"));
                p.setNombre_pasajero(rs.getString("nombre_pasajero"));
                p.setCedula(rs.getString("cedula"));
                p.setEstado_pasajero(rs.getString("estado_pasajero"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener pasajero: " + e.getMessage());
        }
        return p;
    }

    @Override
    public boolean agregarPasajero(Pasajeros pasajeros) {
        String sql = "INSERT INTO pasajero (nombre_pasajero, cedula, estado_pasajero) VALUES (?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pasajeros.getNombre_pasajero());
            ps.setString(2, pasajeros.getCedula());
            ps.setString(3, pasajeros.getEstado_pasajero());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar pasajero: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarPasajero(String cedula, Pasajeros pasajeros) {
        String sql = "UPDATE pasajero SET nombre_pasajero = ?, cedula = ?, estado_pasajero = ? WHERE cedula = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pasajeros.getNombre_pasajero());
            ps.setString(2, pasajeros.getCedula());
            ps.setString(3, pasajeros.getEstado_pasajero());
            ps.setString(4, cedula);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar pasajero: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean cambiarEstadoPasajero(int id_pasajero) {
        String sql = "UPDATE pasajero SET estado_pasajero = CASE WHEN estado_pasajero = 'ACTIVO' THEN 'INACTIVO' ELSE 'Activo' END WHERE id_pasajero = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_pasajero);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al cambiar estado de pasajero: " + e.getMessage());
            return false;
        }
    }
}