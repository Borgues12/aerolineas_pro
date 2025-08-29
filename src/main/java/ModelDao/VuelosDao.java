package ModelDao;

import Interfaces.IntVuelo;
import MODEL.Aviones;
import MODEL.Vuelos;

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
        Vuelos vuelos = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT v.*, a.modelo AS avion FROM vuelo v LEFT JOIN avion a ON  v.id_avion = a.id_avion " +
                            "WHERE v.id_vuelo = ?"
            );
            ps.setInt(1, id_vuelo);
            rs = ps.executeQuery();

            if (rs.next()) {
                vuelos = new Vuelos(
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha"),
                        rs.getString("estado_vuelo"),
                        rs.getInt("id_avion")
                );
                vuelos.setNombre_avion(rs.getString("modelo"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener vuelo por ID: " + e.getMessage());
        } finally {
            config.Conexion.closeResources(con, ps, rs);
        }
        return vuelos;
    }
        @Override
        public boolean agregarVuelo(Vuelos vuelo) {
            String sql = "INSERT INTO vuelo (origen, destino, fecha, estado_vuelo, id_avion) VALUES (?, ?, ?, ?, ?)";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, vuelo.getOrigen());
                ps.setString(2, vuelo.getDestino());
                ps.setDate(3, vuelo.getFecha());
                ps.setString(4, vuelo.getEstado_vuelo());
                ps.setInt(5, vuelo.getId_avion());
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Error al agregar vuelo: " + e.getMessage());
                return false;
            }
        }

        @Override
        public boolean actualizarVuelo(int id_vuelo, Vuelos vuelos) {
            String sql = "UPDATE vuelo SET origen = ?, destino = ?, fecha = ?, estado_vuelo = ?, id_avion = ? WHERE id_vuelo = ?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, vuelos.getOrigen());
                ps.setString(2, vuelos.getDestino());
                ps.setDate(3, vuelos.getFecha());
                ps.setString(4, vuelos.getEstado_vuelo());
                ps.setInt(5, vuelos.getId_avion());
                ps.setInt(6, id_vuelo);
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Error al actualizar vuelo: " + e.getMessage());
                return false;
            }
        }
        @Override
        public boolean cambiarEstadoVuelo(int id_vuelo, String estado_vuelo) {
            String sql = "UPDATE vuelo SET estado = ? WHERE id_vuelo = ?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, estado_vuelo);
                ps.setInt(2, id_vuelo);
                return ps.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("Error al cambiar estado del vuelo: " + e.getMessage());
                return false;
            } finally {
                config.Conexion.closeResources(con, ps, null);
            }
        }
    }
