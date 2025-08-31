package ModelDao;

import Config.Conexion;
import Interfaces.IntVuelo;
import MODEL.Pasajeros;
import MODEL.Reserva;
import MODEL.Vuelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VuelosDao implements IntVuelo {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<Vuelos> todosLosVuelos() {
        List<Vuelos> lista_vuelos = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT v.id_vuelo, v.origen, v.destino, v.fecha_vuelo, v.estado, v.id_avion, " +
                            "a.modelo AS nombre_avion FROM vuelo v " +
                            "LEFT JOIN avion a ON v.id_avion = a.id_avion"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Vuelos v = new Vuelos(
                        rs.getInt("id_vuelo"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha_vuelo"),
                        rs.getString("estado"),
                        rs.getInt("id_avion")
                );
                v.setNombre_avion(rs.getString("nombre_avion"));
                lista_vuelos.add(v);
            }
        } catch (Exception e) {
            System.out.println("Error al listar vuelos: " + e.getMessage());
        } finally {
            Conexion.closeResources(con, ps, rs);
        }
        return lista_vuelos;
    }

    @Override
    public Vuelos obtenerVueloPorId(int id_vuelo) {
        Vuelos vuelo = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT v.id_vuelo, v.origen, v.destino, v.fecha_vuelo, v.estado_vuelo, v.id_avion, " +
                            "a.modelo AS nombre_avion FROM vuelo v " +
                            "LEFT JOIN avion a ON v.id_avion = a.id_avion " +
                            "WHERE v.id_vuelo = ?"
            );
            ps.setInt(1, id_vuelo);
            rs = ps.executeQuery();

            if (rs.next()) {
                vuelo = new Vuelos(
                        rs.getInt("id_vuelo"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha"),
                        rs.getString("estado_vuelo"),
                        rs.getInt("id_avion")
                );
                vuelo.setNombre_avion(rs.getString("nombre_avion"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener vuelo por ID: " + e.getMessage());
        } finally {
            Conexion.closeResources(con, ps, rs);
        }
        return vuelo;
    }
    @Override
    public Vuelos obtenerVueloPorOrigenODestino(String origen, String destino) {
        Vuelos vuelo = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT v.id_vuelo, v.origen, v.destino, v.fecha_vuelo, v.estado_vuelo, v.id_avion, " +
                            "a.modelo AS nombre_avion FROM vuelo v " +
                            "LEFT JOIN avion a ON v.id_avion = a.id_avion " +
                            "WHERE v.origen = ? or v.destino = ?"
            );
            ps.setString(1, origen);
            ps.setString(2, destino);
            rs = ps.executeQuery();

            if (rs.next()) {
                vuelo = new Vuelos(
                        rs.getInt("id_vuelo"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha"),
                        rs.getString("estado_vuelo"),
                        rs.getInt("id_avion")
                );
                vuelo.setNombre_avion(rs.getString("nombre_avion"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener vuelo por ID: " + e.getMessage());
        } finally {
            Conexion.closeResources(con, ps, rs);
        }
        return vuelo;
    }


    @Override
    public Vuelos obtenerVueloConPasajerosPorId(int idVuelo) {
        Vuelos vuelo = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT v.id_vuelo, v.origen, v.destino, v.fecha_vuelo, v.estado, v.id_avion, " +
                            "a.modelo AS nombre_avion, " +
                            "r.id_reserva, r.asiento, r.estado AS estado_reserva, " +
                            "p.id_pasajero, p.nombre_pasajero, p.cedula, p.estado_pasajero " +
                            "FROM vuelo v " +
                            "LEFT JOIN avion a ON v.id_avion = a.id_avion " +
                            "LEFT JOIN reserva r ON v.id_vuelo = r.id_vuelo " +
                            "LEFT JOIN pasajero p ON r.id_pasajero = p.id_pasajero " +
                            "WHERE v.id_vuelo = ?"
            );
            ps.setInt(1, idVuelo);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (vuelo == null) {
                    vuelo = new Vuelos(
                            rs.getInt("id_vuelo"),
                            rs.getString("origen"),
                            rs.getString("destino"),
                            rs.getDate("fecha_vuelo"),
                            rs.getString("estado"),
                            rs.getInt("id_avion")
                    );
                    vuelo.setNombre_avion(rs.getString("nombre_avion"));
                }

                int idPasajero = rs.getInt("p.id_pasajero");
                if (idPasajero != 0) {
                    Pasajeros pasajero = new Pasajeros(
                            rs.getString("nombre_pasajero"),
                            rs.getString("cedula"),
                            rs.getString("estado_pasajero")
                    );

                    Reserva reserva = new Reserva(
                            rs.getInt("id_reserva"),
                            rs.getInt("id_vuelo"),
                            rs.getString("asiento"),
                            rs.getString("estado_reserva")
                    );

                    vuelo.addPasajero(pasajero);
                    vuelo.addReserva(reserva);
                }
            }

        } catch (Exception e) {
            System.out.println("Error obtenerVueloConPasajerosPorId: " + e.getMessage());
        } finally {
            Conexion.closeResources(con, ps, rs);
        }
        return vuelo;
    }

    // Resto de métodos existentes...
    @Override
    public boolean agregarVuelo(Vuelos vuelo) {
        String sql = "INSERT INTO vuelo (origen, destino, fecha, estado, id_avion) VALUES (?, ?, ?, ?, ?)";
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
        } finally {
            Conexion.closeResources(con, ps, null);
        }
    }

    @Override
    public boolean actualizarVuelo(int id_vuelo, Vuelos vuelos) {
        String sql = "UPDATE vuelo SET origen = ?, destino = ?, fecha_vuelo = ?, estado = ?, id_avion = ? WHERE id_vuelo = ?";
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
        } finally {
            Conexion.closeResources(con, ps, null);
        }
    }

    @Override
    public boolean cambiarEstadoVuelo(int id_vuelo) {
        String sql = "UPDATE vuelo " +
                "SET estado = CASE " +
                "WHEN estado = 'DISPONIBLE' THEN 'COMPLETADO' " +
                "WHEN estado = 'COMPLETADO' THEN 'CANCELADO' " +
                "WHEN estado = 'CANCELADO' THEN 'DISPONIBLE' " +
                "ELSE 'DISPONIBLE' " +
                "END " +
                "WHERE id_vuelo = ?;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_vuelo);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al cambiar estado del vuelo: " + e.getMessage());
            return false;
        } finally {
            Conexion.closeResources(con, ps, null);
        }
    }
}