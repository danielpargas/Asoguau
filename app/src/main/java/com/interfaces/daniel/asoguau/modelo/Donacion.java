package com.interfaces.daniel.asoguau.modelo;

/**
 * Created by hanyou on 04/10/15.
 */
public class Donacion {
    private String monto;
    private String nreferencia;
    private String nombre;
    private String fecha;

    public Donacion(String monto, String nreferencia, String nombre, String fecha) {
        this.monto = monto;
        this.nreferencia = nreferencia;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getNreferencia() {
        return nreferencia;
    }

    public void setNreferencia(String nreferencia) {
        this.nreferencia = nreferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
