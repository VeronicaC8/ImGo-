package com.example.veronica.imgo;

public class Puntuacion {
    private int idPuntuacion;
    private String descripcionPuntuacion;

    public Puntuacion() {
    }

    public Puntuacion(int idPuntuacion, String descripcionPuntuacion) {
        this.idPuntuacion = idPuntuacion;
        this.descripcionPuntuacion = descripcionPuntuacion;
    }

    public int getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(int idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public String getDescripcionPuntuacion() {
        return descripcionPuntuacion;
    }

    public void setDescripcionPuntuacion(String descripcionPuntuacion) {
        this.descripcionPuntuacion = descripcionPuntuacion;
    }
}
