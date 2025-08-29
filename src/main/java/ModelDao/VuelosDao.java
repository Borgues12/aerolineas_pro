package ModelDao;

import Interfaces.IntAviones;
import Interfaces.IntVuelo;
import MODEL.Aviones;
import MODEL.Vuelos;
import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VuelosDao implements IntVuelo {
        config.Conexion cn = new config.Conexion();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;

    @Override
    public List<Vuelos> todosLosVuelos() {
        List<Vuelos> lista_vuelos = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT v.*, a.modelo AS modelo FROM vuelo v LEFT JOIN avion a ON v.id_avion = a.id_avion"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Vuelos v = new Vuelos(
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha"),
                        rs.getString("estado_vuelo"),
                        rs.getInt("id_avion")
                );
                v.setNombre_avion(rs.getString("modelo"));
                lista_vuelos.add(v);
            }
        } catch (Exception e) {
            System.out.println("Error al listar vuelos: " + e.getMessage());
        } finally {
            config.Conexion.closeResources(con, ps, rs);
        }
        return lista_vuelos;
    }


    @Override
        public Vuelos obtenerVueloPorId(int id_vuelo) {
            Aviones a = null;
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(
                        "SELECT * FROM avion WHERE id_avion = ?"
                );
                ps.setInt(1, id_avion);
                rs = ps.executeQuery();
                if (rs.next()) {
                    a = new Aviones();
                    a.setId_avion(rs.getInt("id_avion"));
                    a.setModelo(rs.getString("modelo"));
                    a.setCapacidad(rs.getInt("capacidad"));
                    a.setEstado_avion(rs.getString("estado_avion"));
                }
            } catch (Exception e) {
                System.out.println("Error al obtener avion: " + e.getMessage());
            }
            return a;
        }

        @Override
        public boolean agregarAvion(Aviones avion) {
            String sql = "INSERT INTO avion (modelo, capacidad, estado_avion) VALUES (?, ?, ?)";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, avion.getModelo());
                ps.setInt(2, avion.getCapacidad());
                ps.setString(3, avion.getEstado_avion());
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Error al agregar avion: " + e.getMessage());
                return false;
            }
        }

        @Override
        public boolean actualizarAvion(int id_avion, Aviones avion) {
            String sql = "UPDATE avion SET modelo = ?, capacidad = ?, estado_avion = ? WHERE id_avion = ?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, avion.getModelo());
                ps.setInt(2, avion.getCapacidad());
                ps.setString(3, avion.getEstado_avion());
                ps.setInt(4, id_avion);
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Error al actualizar avion: " + e.getMessage());
                return false;
            }
        }
        @Override
        public boolean cambiarEstadoAvion(int id_avion, String estado) {
            String sql = "UPDATE avion SET ESTADO_AVION = CASE WHEN ESTADO_AVION = 'ACTIVO' THEN 'INACTIVO' ELSE 'Activo' END WHERE ID_AVION = ?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, id_avion);
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Error al cambiar estado de avion: " + e.getMessage());
                return false;
            }
        }
    }

}
