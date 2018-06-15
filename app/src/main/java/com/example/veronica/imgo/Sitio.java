package com.example.veronica.imgo;

public class Sitio {
    private int idSitio;
    private int idUsuario;
    private String descripcion;
    private String nombreSitio;
    private float precioMax;
    private float precioMin;

    public Sitio() {
    }

    public Sitio(int idSitio, int idUsuario, String descripcion, String nombreSitio, float precioMax, float precioMin) {
        this.idSitio = idSitio;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.nombreSitio = nombreSitio;
        this.precioMax = precioMax;
        this.precioMin = precioMin;
    }

    public int getIdSitio() {
        return idSitio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public float getPrecioMax() {
        return precioMax;
    }

    public float getPrecioMin() {
        return precioMin;
    }

    public void setIdSitio(int idSitio) {
        this.idSitio = idSitio;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public void setPrecioMax(float precioMax) {
        this.precioMax = precioMax;
    }

    public void setPrecioMin(float precioMin) {
        this.precioMin = precioMin;
    }

}