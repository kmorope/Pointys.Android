package com.konradlorenz.pointys;

/**
 * Created by wenikore on 17/11/16.
 */

public class Lista {
    public String Lugar;
    public String ubicacion;
    public double distancia;



    public Lista(String lugar, String ubicacion, double distancia) {
        Lugar = lugar;
        this.ubicacion = ubicacion;
        this.distancia = distancia;

    }

    public String getLugar() {
        return Lugar;
    }
    public void setLugar(String lugar) {
        Lugar = lugar;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public double getdistancia() {
        return distancia;
    }
    public void setdistancia(double latitud) {
        this.distancia = latitud;
    }

}
