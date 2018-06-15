package com.example.veronica.imgo;

public class PuntuacionSitio {
    private int idPuntuacionSitio;
    private int idPuntuacion;
    private int idSitio;
    private String resena;

    public PuntuacionSitio() {
    }

    public PuntuacionSitio(int idPuntuacionSitio, int idPuntuacion, int idSitio, String resena) {
        this.idPuntuacionSitio = idPuntuacionSitio;
        this.idPuntuacion = idPuntuacion;
        this.idSitio = idSitio;
        this.resena = resena;
    }

    public int getIdPuntuacionSitio() {
        return idPuntuacionSitio;
    }

    public void setIdPuntuacionSitio(int idPuntuacionSitio) {
        this.idPuntuacionSitio = idPuntuacionSitio;
    }

    public int getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(int idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public int getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(int idSitio) {
        this.idSitio = idSitio;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }
}
