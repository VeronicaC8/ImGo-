package com.example.veronica.imgo;

public class Ubicacion {
    private int idUbicacion;
    private int idSitio;
    private String direccion;
    private float coordenadaX;
    private float coordenadaY;

    public Ubicacion() {
    }

    public Ubicacion(int idUbicacion, int idSitio, String direccion, float coordenadaX, float coordenadaY) {
        this.idUbicacion = idUbicacion;
        this.idSitio = idSitio;
        this.direccion = direccion;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(int idSitio) {
        this.idSitio = idSitio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(float coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public float getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(float coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
