package Controller;

import ModelDao.AvionesDao;
import MODEL.Aviones;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AvionesController", urlPatterns = {"/AvionesController"})
public class AvionesController extends HttpServlet {

    private AvionesDao dao = new AvionesDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                listarAviones(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "cambiarEstado":
                cambiarEstadoAvion(request, response);
                break;
            default:
                listarAviones(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":
                crearAvion(request, response);
                break;
            case "actualizar":
                actualizarAvion(request, response);
                break;
            default:
                response.sendRedirect("AvionesController?accion=listar");
                break;
        }
    }

    private void listarAviones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Aviones> listaAviones = dao.todosLosAviones();
            request.setAttribute("aviones", listaAviones);
            request.getRequestDispatcher("views/aviones/listar.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar la lista de aviones: " + e.getMessage());
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("views/aviones/formulario.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar el formulario: " + e.getMessage());
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAvion = Integer.parseInt(request.getParameter("id"));
            Aviones avion = dao.obtenerAvionporId(idAvion);

            if (avion != null) {
                request.setAttribute("avion", avion);
                request.getRequestDispatcher("views/aviones/formulario.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Avión no encontrado");
                response.sendRedirect("AvionesController?accion=listar");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de avión inválido");
            response.sendRedirect("AvionesController?accion=listar");
        }
    }

    private void crearAvion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String modelo = request.getParameter("modelo");
            int capacidad = Integer.parseInt(request.getParameter("capacidad"));
            String estadoAvion = request.getParameter("estado_avion");

            if (modelo != null && !modelo.trim().isEmpty()) {

                Aviones nuevoAvion = new Aviones();
                nuevoAvion.setModelo(modelo);
                nuevoAvion.setCapacidad(capacidad);
                nuevoAvion.setEstado_avion(estadoAvion);

                boolean resultado = dao.agregarAvion(nuevoAvion);

                if (resultado) {
                    request.getSession().setAttribute("mensaje", "Avión creado exitosamente");
                } else {
                    request.getSession().setAttribute("error", "Error al crear el avión");
                }
            } else {
                request.getSession().setAttribute("error", "Datos incompletos");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        response.sendRedirect("AvionesController?accion=listar");
    }

    private void actualizarAvion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAvion = Integer.parseInt(request.getParameter("id_avion"));
            String modelo = request.getParameter("modelo");
            int capacidad = Integer.parseInt(request.getParameter("capacidad"));
            String estadoAvion = request.getParameter("estado_avion");

            if (modelo != null && !modelo.trim().isEmpty()) {

                Aviones avion = new Aviones();
                avion.setModelo(modelo);
                avion.setCapacidad(capacidad);
                avion.setEstado_avion(estadoAvion);

                boolean resultado = dao.actualizarAvion(idAvion, avion);

                if (resultado) {
                    request.getSession().setAttribute("mensaje", "Avión actualizado exitosamente");
                } else {
                    request.getSession().setAttribute("error", "Error al actualizar el avión");
                }
            } else {
                request.getSession().setAttribute("error", "Datos incompletos");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        response.sendRedirect("AvionesController?accion=listar");
    }

    private void cambiarEstadoAvion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAvion = Integer.parseInt(request.getParameter("id"));

            boolean resultado = dao.cambiarEstadoAvion(idAvion);

            if (resultado) {
                request.getSession().setAttribute("mensaje", "Estado del avión cambiado exitosamente");
            } else {
                request.getSession().setAttribute("error", "Error al cambiar el estado del avión");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID de avión inválido");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        response.sendRedirect("AvionesController?accion=listar");
    }
}