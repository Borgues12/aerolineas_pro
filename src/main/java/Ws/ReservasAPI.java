package Ws;

import MODEL.Aviones;
import MODEL.Reserva;
import ModelDao.AvionesDao;
import ModelDao.ReservasDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reservas")
    public class ReservasAPI {
    private ReservasDao reservasDao = new ReservasDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todosLosAviones() {
        List<Reserva> reservas = reservasDao.todasLasReservas();
        return Response.ok(reservas).build();
    }

    @GET
    @Path("/{id_reserva}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReservaPorId(@PathParam("id_reserva") int idReserva) {
        Reserva reserva = reservasDao.obtenerReservaPorId(idReserva);
        return Response.ok(reserva).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarReserva(Reserva reserva) {
        boolean resultado = reservasDao.agregarReserva(reserva);
        if (resultado) {
            return Response.status(Response.Status.CREATED).entity(reserva).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id_reserva}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarReserva(@PathParam("id_reserva") int idReserva, Reserva reserva) {
        boolean resultado = reservasDao.actualizarReserva(idReserva, reserva);
        if (resultado) {
            return Response.ok(reserva).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id_reserva}")
    public Response cambiarEstadoReserva(@PathParam("id_reserva") int idReserva) {
        boolean resultado = reservasDao.cambiarEstadoReserva(idReserva);
        if (resultado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}



