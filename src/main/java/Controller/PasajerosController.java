package Controller;

import MODEL.Pasajeros;
import ModelDao.PasajerosDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PasajerosController", urlPatterns = {"/PasajerosController"})
public class PasajerosController extends HttpServlet {

    private final PasajerosDao pasajerosDao = new PasajerosDao();
    private final String DASHBOARD_PASAJEROS = "dashboard_pasajeros.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String acceso = DASHBOARD_PASAJEROS;

        switch (action != null ? action : "") {
            case "pasajeros_page":
                // Cargar lista de pasajeros por defecto
                List<Pasajeros> listaPasajeros = pasajerosDao.todosLosPasajeros();
                request.setAttribute("listaPasajeros", listaPasajeros);
                acceso = DASHBOARD_PASAJEROS;
                break;

            case "lista_pasajeros":
                List<Pasajeros> pasajeros = pasajerosDao.todosLosPasajeros();
                request.setAttribute("listaPasajeros", pasajeros);
                acceso = DASHBOARD_PASAJEROS;
                break;

            case "buscar_pasajero":
                String cedula = request.getParameter("cedula");
                if (cedula != null && !cedula.trim().isEmpty()) {
                    Pasajeros pasajero = pasajerosDao.obtenerPasajeroPorCedula(cedula);
                    if (pasajero != null) {
                        request.setAttribute("pasajeroEncontrado", pasajero);
                        request.setAttribute("mensaje", "Pasajero encontrado");
                    } else {
                        request.setAttribute("error", "No se encontró pasajero con cédula: " + cedula);
                    }
                } else {
                    request.setAttribute("error", "Debe proporcionar una cédula válida");
                }
                acceso = DASHBOARD_PASAJEROS;
                break;

            case "cambiar_estado_pasajero":
                String idPasajeroStr = request.getParameter("id_pasajero");
                String estadoPasajero = request.getParameter("estado_pasajero");

                if (idPasajeroStr != null) {
                    try {
                        int id_pasajero_con = Integer.parseInt(idPasajeroStr);
                        boolean resultado = pasajerosDao.cambiarEstadoPasajero(id_pasajero_con);

                        if (resultado) {
                            request.setAttribute("mensaje", "Estado del pasajero actualizado correctamente");
                        } else {
                            request.setAttribute("error", "Error al cambiar el estado del pasajero");
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "ID de pasajero inválido");
                    }
                }

                // Recargar lista después de cambiar estado
                List<Pasajeros> pasajerosActualizados = pasajerosDao.todosLosPasajeros();
                request.setAttribute("listaPasajeros", pasajerosActualizados);
                acceso = DASHBOARD_PASAJEROS;
                break;

            default:
                // Acción por defecto: mostrar lista de pasajeros
                List<Pasajeros> defaultPasajeros = pasajerosDao.todosLosPasajeros();
                request.setAttribute("listaPasajeros", defaultPasajeros);
                acceso = DASHBOARD_PASAJEROS;
                break;
        }

        if (!response.isCommitted()) {
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action != null ? action : "") {
            case "crear_pasajero":
                String nombre = request.getParameter("nombre_pasajero");
                String cedula = request.getParameter("cedula");
                String estado = request.getParameter("estado_pasajero");

                // Validar datos
                if (nombre != null && !nombre.trim().isEmpty() &&
                        cedula != null && !cedula.trim().isEmpty()) {

                    // Crear objeto Pasajeros
                    Pasajeros nuevoPasajero = new Pasajeros();
                    nuevoPasajero.setNombre_pasajero(nombre.trim());
                    nuevoPasajero.setCedula(cedula.trim());
                    nuevoPasajero.setEstado_pasajero(estado != null ? estado : "ACTIVO");

                    // Intentar agregar
                    boolean resultado = pasajerosDao.agregarPasajero(nuevoPasajero);

                    if (resultado) {
                        request.setAttribute("mensaje", "Pasajero creado exitosamente");
                    } else {
                        request.setAttribute("error", "Error al crear el pasajero");
                    }
                } else {
                    request.setAttribute("error", "Nombre y cédula son campos obligatorios");
                }
                break;

            case "actualizar_pasajero":
                String cedulaActualizar = request.getParameter("cedula_original");
                String nombreActualizar = request.getParameter("nombre_pasajero");
                String nuevaCedula = request.getParameter("cedula");
                String estadoActualizar = request.getParameter("estado_pasajero");

                if (cedulaActualizar != null && nombreActualizar != null && nuevaCedula != null) {
                    Pasajeros pasajeroActualizar = new Pasajeros();
                    pasajeroActualizar.setNombre_pasajero(nombreActualizar.trim());
                    pasajeroActualizar.setCedula(nuevaCedula.trim());
                    pasajeroActualizar.setEstado_pasajero(estadoActualizar != null ? estadoActualizar : "ACTIVO");

                    boolean resultado = pasajerosDao.actualizarPasajero(cedulaActualizar, pasajeroActualizar);

                    if (resultado) {
                        request.setAttribute("mensaje", "Pasajero actualizado exitosamente");
                    } else {
                        request.setAttribute("error", "Error al actualizar el pasajero");
                    }
                } else {
                    request.setAttribute("error", "Datos incompletos para actualizar");
                }
                break;

            default:
                request.setAttribute("error", "Acción no válida");
                break;
        }

        // Recargar lista de pasajeros después de cualquier operación POST
        List<Pasajeros> pasajeros = pasajerosDao.todosLosPasajeros();
        request.setAttribute("listaPasajeros", pasajeros);

        if (!response.isCommitted()) {
            RequestDispatcher vista = request.getRequestDispatcher(DASHBOARD_PASAJEROS);
            vista.forward(request, response);
        }
    }
}
