package ModelDao;

import Config.Conexion;
import Interfaces.IntReservas;
import MODEL.Reserva;
import MODEL.Vuelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservasDao implements IntReservas {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<Reserva> todasLasReservas() {
        List<Reserva> lista_reservas = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT " +
                            "r.id_reserva, " +
                            "r.id_vuelo, " +  // Para el constructor
                            "r.id_pasajero, " + // Para el constructor
                            "r.asiento, " +
                            "r.estado, " +
                            "CONCAT(v.origen, ' a ', v.destino) AS nombre_vuelo, " +
                            "p.nombre_pasajero " +
                            "FROM reserva r " +
                            "INNER JOIN vuelo v ON r.id_vuelo = v.id_vuelo " +
                            "INNER JOIN pasajero p ON r.id_pasajero = p.id_pasajero");
            rs = ps.executeQuery();
            while (rs.next()) {
                // Crear la reserva con los IDs (como pide el constructor)
                Reserva reserva = new Reserva(
                        rs.getInt("id_vuelo"),
                        rs.getInt("id_pasajero"),
                        rs.getString("asiento"),
                        rs.getString("estado")
                );

                // Agregar los nombres descriptivos usando métodos setter
                // (debes agregar estos atributos y métodos en tu clase Reserva)
                reserva.setNombreVuelo(rs.getString("nombre_vuelo"));
                reserva.setNombrePasajero(rs.getString("nombre_pasajero"));

                lista_reservas.add(reserva);
            }
        } catch (Exception e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        } finally {
            Conexion.closeResources(con, ps, rs);
        }
        return lista_reservas;
    }
    @Override
    public Reserva obtenerReservaPorId(int id_reserva) {
        Reserva reserva = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(
                    "SELECT " +
                            "r.id_reserva, " +
                            "r.id_vuelo, " +
                            "r.id_pasajero, " +
                            "r.asiento, " +
                            "r.estado, " +
                            "CONCAT(v.origen, ' a ', v.destino) AS nombre_vuelo, " +
                            "p.nombre_pasajero " +
                            "FROM reserva r " +
                            "INNER JOIN vuelo v ON r.id_vuelo = v.id_vuelo " +
                            "INNER JOIN pasajero p ON r.id_pasajero = p.id_pasajero " +
                            "WHERE r.id_reserva = ?");

            ps.setInt(1, id_reserva);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Crear la reserva con los IDs (como pide el constructor)
                reserva = new Reserva(
                        rs.getInt("id_vuelo"),
                        rs.getInt("id_pasajero"),
                        rs.getString("asiento"),
                        rs.getString("estado")
                );

                // Agregar los nombres descriptivos
                reserva.setNombreVuelo(rs.getString("nombre_vuelo"));
                reserva.setNombrePasajero(rs.getString("nombre_pasajero"));

                // Si necesitas el ID de la reserva también
                // reserva.setIdReserva(rs.getInt("id_reserva"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener reserva por ID: " + e.getMessage());
        } finally {
            Conexion.closeResources(con, ps, rs);
        }
        return reserva;
    }
    @Override
    public boolean agregarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_vuelo, id_pasajero, asiento, estado_reserva) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, reserva.getId_vuelo());
            ps.setInt(2, reserva.getId_pasajero());
            ps.setString(3, reserva.getAsiento());
            ps.setString(4, reserva.getEstado_reserva());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar reserva: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarReserva(int id_reserva, Reserva reserva) {
        String sql = "UPDATE reserva SET id_vuelo = ?, id_pasajero = ?, asiento = ?, estado_reserva = ? WHERE id_reserva = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, reserva.getId_vuelo());
            ps.setInt(2, reserva.getId_pasajero());
            ps.setString(3, reserva.getAsiento());
            ps.setString(4, reserva.getEstado_reserva());
            ps.setInt(5, id_reserva);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean cambiarEstadoReserva(int id_reserva) {
        String sql = "UPDATE reserva " +
                "SET estado = CASE " +
                "WHEN estado = 'REALIZADA' THEN 'RETRASADO' " +
                "WHEN estado = 'RETRASADO' THEN 'CANCELADA' " +
                "WHEN estado = 'CANCELADA' THEN 'REALIZADA' " +
                "ELSE 'REALIZADA' END " +
                "WHERE id_reserva = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_reserva);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al cambiar estado de la reserva: " + e.getMessage());
            return false;
        }
    }
}
