package MODEL;

public class Pasajeros {
    private int id_pasajero;
    private String nombre_pasajero;
    private String cedula;
    private String estado_pasajero;

    public Pasajeros() {
    }

    public Pasajeros(String nombre_pasajero, String cedula, String estado_pasajero) {
        this.nombre_pasajero = nombre_pasajero;
        this.cedula = cedula;
        this.estado_pasajero = estado_pasajero;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public String getNombre_pasajero() {
        return nombre_pasajero;
    }

    public void setNombre_pasajero(String nombre_pasajero) {
        this.nombre_pasajero = nombre_pasajero;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstado_pasajero() {
        return estado_pasajero;
    }

    public void setEstado_pasajero(String estado_pasajero) {
        this.estado_pasajero = estado_pasajero;
    }
}




