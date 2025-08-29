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
    public class AvionesDao implements IntAviones {
        config.Conexion cn = new config.Conexion();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;

        @Override
        public List<Vuelos> todoslosVuelos() {
            List<Vuelos> lista_vuelos = new ArrayList<>();
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(
                        "SELECT * FROM vuelo"
                );
                rs = ps.executeQuery();
                while (rs.next()) {
                    Vuelos v = new Vuelos();
                    v.setId_vuelo(rs.getInt("id_vuelo"));
                    v.setOrigen(rs.getString("origen"));
                    v.setDestino(rs.getString("destino"));
                    v.setFecha(rs.getDate("fecha"));
                    v.setEstado_vuelo(rs.getString("estado_vuelo"));
                    v.setId_avion(rs.getInt("id_avion"));
                    lista_vuelos.add(v);
                }
            } catch (Exception e) {
                System.out.println("Error al listar aviones: " + e.getMessage());
            }
            return lista_vuelos;
        }

        @Override
        public Aviones obtenerAvionporId(int id_avion) {
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
