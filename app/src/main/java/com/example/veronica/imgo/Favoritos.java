package com.example.veronica.imgo;

/**
 * Created by Oscar on 10/06/2018.
 */

public class Favoritos {

    private String nombreSitio;
    private String descripcion;
    private int Bandera;

    public Favoritos() {
    }

    public Favoritos(String nombreSitio, String descripcion, int bandera) {
        this.nombreSitio = nombreSitio;
        this.descripcion = descripcion;
        Bandera = bandera;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBandera() {
        return Bandera;
    }

    public void setBandera(int bandera) {
        Bandera = bandera;
    }
}
