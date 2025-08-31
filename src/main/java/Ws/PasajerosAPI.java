package Ws;


import MODEL.Pasajeros;
import ModelDao.PasajerosDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pasajeros")
public class PasajerosAPI {
    private PasajerosDao pasajerosDao = new PasajerosDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todosLosPasajeros() {
        List<Pasajeros> pass = pasajerosDao.todosLosPasajeros();
        return Response.ok(pass).build();
    }

    @GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPasajeroPorCedula(@PathParam("cedula") String cedula) {
        Pasajeros pass = pasajerosDao.obtenerPasajeroPorCedula(cedula);
        if (pass != null) {
            return Response.ok(pass).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarPasajero(Pasajeros pasajero) {
        boolean resultado = pasajerosDao.agregarPasajero(pasajero);
        if (resultado) {
            return Response.status(Response.Status.CREATED).entity(pasajero).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPasajero(@PathParam("cedula") String cedula, Pasajeros pasajero) {
        boolean resultado = pasajerosDao.actualizarPasajero(cedula, pasajero);
        if (resultado) {
            return Response.ok(pasajero).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id_pasajero}")
    public Response cambiarEstadoPasajero(@PathParam("id_pasajero") int id_pasajero) {
        boolean resultado = pasajerosDao.cambiarEstadoPasajero(id_pasajero);
        if (resultado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}


