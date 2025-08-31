package Controller;

import ModelDao.VuelosDao;
import ModelDao.AvionesDao;
import MODEL.Vuelos;
import MODEL.Aviones;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "VuelosController", urlPatterns = {"/VuelosController"})
public class VuelosController extends HttpServlet {

    private VuelosDao dao = new VuelosDao();
    private AvionesDao avionesDao = new AvionesDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                listarVuelos(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "ver":
                verVueloConPasajeros(request, response);
                break;
            case "cambiarEstado":
                cambiarEstadoVuelo(request, response);
                break;
            case "buscar":
                buscarVuelo(request, response);
                break;
            default:
                listarVuelos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":
                crearVuelo(request, response);
                break;
            case "actualizar":
                actualizarVuelo(request, response);
                break;
            default:
                response.sendRedirect("VuelosController?accion=listar");
                break;
        }
    }

    private void listarVuelos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Vuelos> listaVuelos = dao.todosLosVuelos();
            request.setAttribute("vuelos", listaVuelos);
            request.getRequestDispatcher("views/vuelos/listar.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar la lista de vuelos: " + e.getMessage());
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Aviones> listaAviones = avionesDao.todosLosAviones();
            request.setAttribute("aviones", listaAviones);
            request.getRequestDispatcher("views/vuelos/formulario.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar los aviones: " + e.getMessage());
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVuelo = Integer.parseInt(request.getParameter("id"));
            Vuelos vuelo = dao.obtenerVueloPorId(idVuelo);
            List<Aviones> listaAviones = avionesDao.todosLosAviones();

            if (vuelo != null) {
                request.setAttribute("vuelo", vuelo);
                request.setAttribute("aviones", listaAviones);
                request.getRequestDispatcher("views/vuelos/formulario.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Vuelo no encontrado");
                response.sendRedirect("VuelosController?accion=listar");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de vuelo inválido");
            response.sendRedirect("VuelosController?accion=listar");
        }
    }

    private void verVueloConPasajeros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVuelo = Integer.parseInt(request.getParameter("id"));
            Vuelos vuelo = dao.obtenerVueloConPasajerosPorId(idVuelo);

            if (vuelo != null) {
                request.setAttribute("vuelo", vuelo);
                request.getRequestDispatcher("views/vuelos/detalle.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Vuelo no encontrado");
                response.sendRedirect("VuelosController?accion=listar");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de vuelo inválido");
            response.sendRedirect("VuelosController?accion=listar");
        }
    }

    private void buscarVuelo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");

            Vuelos vuelo = dao.obtenerVueloPorOrigenODestino(origen, destino);

            if (vuelo != null) {
                request.setAttribute("vuelo", vuelo);
                request.setAttribute("mensaje", "Vuelo encontrado");
            } else {
                request.setAttribute("error", "No se encontró ningún vuelo con esos criterios");
            }

            request.getRequestDispatcher("views/vuelos/buscar.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error en la búsqueda: " + e.getMessage());
            request.getRequestDispatcher("views/vuelos/buscar.jsp").forward(request, response);
        }
    }

    private void crearVuelo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");
            Date fecha = Date.valueOf(request.getParameter("fecha"));
            String estadoVuelo = request.getParameter("estado_vuelo");
            int idAvion = Integer.parseInt(request.getParameter("id_avion"));

            if (origen != null && !origen.trim().isEmpty() &&
                    destino != null && !destino.trim().isEmpty()) {

                Vuelos nuevoVuelo = new Vuelos();
                nuevoVuelo.setOrigen(origen);
                nuevoVuelo.setDestino(destino);
                nuevoVuelo.setFecha(fecha);
                nuevoVuelo.setEstado_vuelo(estadoVuelo);
                nuevoVuelo.setId_avion(idAvion);

                boolean resultado = dao.agregarVuelo(nuevoVuelo);

                if (resultado) {
                    request.getSession().setAttribute("mensaje", "Vuelo creado exitosamente");
                } else {
                    request.getSession().setAttribute("error", "Error al crear el vuelo");
                }
            } else {
                request.getSession().setAttribute("error", "Datos incompletos");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        response.sendRedirect("VuelosController?accion=listar");
    }

    private void actualizarVuelo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVuelo = Integer.parseInt(request.getParameter("id_vuelo"));
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");
            Date fecha = Date.valueOf(request.getParameter("fecha"));
            String estadoVuelo = request.getParameter("estado_vuelo");
            int idAvion = Integer.parseInt(request.getParameter("id_avion"));

            if (origen != null && !origen.trim().isEmpty() &&
                    destino != null && !destino.trim().isEmpty()) {

                Vuelos vuelo = new Vuelos();
                vuelo.setOrigen(origen);
                vuelo.setDestino(destino);
                vuelo.setFecha(fecha);
                vuelo.setEstado_vuelo(estadoVuelo);
                vuelo.setId_avion(idAvion);

                boolean resultado = dao.actualizarVuelo(idVuelo, vuelo);

                if (resultado) {
                    request.getSession().setAttribute("mensaje", "Vuelo actualizado exitosamente");
                } else {
                    request.getSession().setAttribute("error", "Error al actualizar el vuelo");
                }
            } else {
                request.getSession().setAttribute("error", "Datos incompletos");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        response.sendRedirect("VuelosController?accion=listar");
    }

    private void cambiarEstadoVuelo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVuelo = Integer.parseInt(request.getParameter("id"));

            boolean resultado = dao.cambiarEstadoVuelo(idVuelo);

            if (resultado) {
                request.getSession().setAttribute("mensaje", "Estado del vuelo cambiado exitosamente");
            } else {
                request.getSession().setAttribute("error", "Error al cambiar el estado del vuelo");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID de vuelo inválido");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        response.sendRedirect("VuelosController?accion=listar");
    }
}
