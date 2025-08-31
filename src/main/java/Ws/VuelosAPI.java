package Ws;

import MODEL.Vuelos;
import ModelDao.VuelosDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vuelos")
    public class VuelosAPI {
    private VuelosDao vuelosDao = new VuelosDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todosLosVuelos() {
        List<Vuelos> vuelos = vuelosDao.todosLosVuelos();
        return Response.ok(vuelos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVueloConPasajerosPorId(@PathParam("id") int idVuelo) {
        Vuelos vuelo = vuelosDao.obtenerVueloConPasajerosPorId(idVuelo);
        return Response.ok(vuelo).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarVuelo(Vuelos vuelo) {
        boolean resultado = vuelosDao.agregarVuelo(vuelo);
        if (resultado) {
            return Response.status(Response.Status.CREATED).entity(vuelo).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarVuelo(@PathParam("id") int idVuelo, Vuelos vuelo) {
        boolean resultado = vuelosDao.actualizarVuelo(idVuelo, vuelo);
        if (resultado) {
            return Response.ok(vuelo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id_vuelo}")
    public Response cambiarEstadoVuelo(@PathParam("id_vuelo") int id_vuelo) {
        boolean resultado = vuelosDao.cambiarEstadoVuelo(id_vuelo);
        if (resultado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
