package MODEL;

import java.sql.Date;

public class Vuelos {
    private int id_vuelo;
    private String origen;
    private String destino;
    private Date fecha;
    private String estado_vuelo;
    private int id_avion;
    private String nombre_avion;

    public Vuelos() {
    }

    public Vuelos(String origen, String destino, Date fecha, String estado_vuelo, int id_avion) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.estado_vuelo = estado_vuelo;
        this.id_avion = id_avion;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado_vuelo() {
        return estado_vuelo;
    }

    public void setEstado_vuelo(String estado_vuelo) {
        this.estado_vuelo = estado_vuelo;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getNombre_avion() {
        return nombre_avion;
    }

    public void setNombre_avion(String nombre_avion) {
        this.nombre_avion = nombre_avion;
    }
}
