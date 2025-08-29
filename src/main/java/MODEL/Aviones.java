package MODEL;

public class Aviones {
    private int id_avion;
    private String modelo;
    private int capacidad;
    private String estado_avion;

    public Aviones() {
    }

    public Aviones(String modelo, int capacidad, String estado_avion) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.estado_avion = estado_avion;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado_avion() {
        return estado_avion;
    }

    public void setEstado_avion(String estado_avion) {
        this.estado_avion = estado_avion;
    }
}
