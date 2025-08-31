package Interfaces;

import MODEL.Vuelos;

import java.util.List;

public interface IntVuelo {
    public List<Vuelos> todosLosVuelos();
    public Vuelos obtenerVueloPorId(int id_vuelo);
    public Vuelos obtenerVueloPorOrigenODestino(String origen, String destino);
    public boolean agregarVuelo(Vuelos vuelo);
    public boolean actualizarVuelo(int id_vuelo, Vuelos vuelo);
    public boolean cambiarEstadoVuelo(int id_vuelo);
    public Vuelos obtenerVueloConPasajerosPorId(int idVuelo);
}
