package Interfaces;

import MODEL.Vuelos;

import java.util.List;

public interface IntVuelo {
    public List<Vuelos> todosLosVuelos();
    public Vuelos obtenerVueloPorId(int id_vuelo);
    public boolean agregarVuelo(Vuelos vuelo);
    public boolean actualizarVuelo(int id_vuelo, Vuelos vuelo);
    public boolean cambiarEstadoVuelo(int id_vuelo, String estado_vuelo);
}
