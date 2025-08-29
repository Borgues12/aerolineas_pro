package Interfaces;

import MODEL.Aviones;

import java.util.List;

public interface IntAviones {
    public List<Aviones> todosLosAviones();
    public Aviones obtenerAvionporId(int id_avion);
    public boolean agregarAvion(Aviones avion);
    public boolean actualizarAvion(int id_avion, Aviones avion);
    public boolean cambiarEstadoAvion(int id_avion, String estado);
}
