package Interfaces;

import MODEL.Pasajeros;
import MODEL.Vuelos;

import java.util.List;

public interface IntPasajeros {
    public List<Pasajeros> todosLosPasajeros();
    public Pasajeros obtenerPasajeroPorCedula(String cedula);
    public boolean agregarPasajero(Pasajeros pasajero);
    public boolean actualizarPasajero(String cedula, Pasajeros pasajero);
    public boolean cambiarEstadoPasajero(int id_pasajero);
}
