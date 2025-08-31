package Interfaces;

import MODEL.Reserva;

import java.util.List;

public interface IntReservas {
    public List<Reserva> todasLasReservas();
    public Reserva obtenerReservaPorId(int id_reserva);
    public boolean agregarReserva(Reserva reserva);
    public boolean actualizarReserva(int id_reserva, Reserva reserva);
    public boolean cambiarEstadoReserva(int id_reserva);
}
