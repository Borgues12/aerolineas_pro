package Ws;

import MODEL.Aviones;
import ModelDao.AvionesDao;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import javax.ws.rs.*;

@Path("/aviones")
public class AvionesAPI {
    private AvionesDao avionesDao = new AvionesDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todosLosAviones() {
        List<Aviones> aviones = avionesDao.todosLosAviones();
        return Response.ok(aviones).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAvionPorId(@PathParam("id") int idAvion) {
        Aviones avion = avionesDao.obtenerAvionporId(idAvion);
        return Response.ok(avion).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarAvion(Aviones avion) {
        boolean resultado = avionesDao.agregarAvion(avion);
        if (resultado) {
            return Response.status(Response.Status.CREATED).entity(avion).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarAvion(@PathParam("id") int idAvion, Aviones avion) {
        boolean resultado = avionesDao.actualizarAvion(idAvion, avion);
        if (resultado) {
            return Response.ok(avion).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response cambiarEstadoAvion(@PathParam("id") int idAvion) {
        boolean resultado = avionesDao.cambiarEstadoAvion(idAvion);
        if (resultado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}