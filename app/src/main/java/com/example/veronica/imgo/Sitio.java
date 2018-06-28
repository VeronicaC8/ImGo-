package com.example.veronica.imgo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Sitio implements Serializable{
    private int idSitio;
    private int idCategoria;
    private String descripcion;
    private String nombreSitio;
    private float precioMax;
    private float precioMin;
    private String imagen;
    private Bitmap imagen2;
    private String dato;

    public Sitio() {
    }

    public Sitio(int idSitio, int idCategoria, String descripcion, String nombreSitio, float precioMax, float precioMin, String imagen, Bitmap imagen2, String dato) {
        this.idSitio = idSitio;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.nombreSitio = nombreSitio;
        this.precioMax = precioMax;
        this.precioMin = precioMin;
        this.imagen = imagen;
        this.imagen2 = imagen2;
        this.dato = dato;
    }


    public int getIdSitio() {
        return idSitio;
    }


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombreSitio;
    }


    public Bitmap getImagen2() {
        return imagen2;
    }

    public void setImagen2(Bitmap imagen2) {
        this.imagen2 = imagen2;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            byte[] byteCode= Base64.decode(dato,Base64.DEFAULT);
            //this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

            int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen2=Bitmap.createScaledBitmap(foto,alto,ancho,true);


        }catch (Exception e){
            e.printStackTrace();

        }
    }
}


