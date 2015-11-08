package com.interfaces.daniel.asoguau.modelo;

/**
 * Created by hanyou on 04/10/15.
 */
public class Donacion {
    private String iddonacion;
    private String idusuario;
    private String idstatusdonacion;
    private String nreferencia;
    private String fecha;
    private String monto;


    public Donacion(String iddonacion, String idusuario, String idstatusdonacion, String nreferencia, String fecha, String monto) {
        this.iddonacion = iddonacion;
        this.idusuario = idusuario;
        this.idstatusdonacion = idstatusdonacion;
        this.nreferencia = nreferencia;
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getIddonacion() {
        return iddonacion;
    }

    public void setIddonacion(String iddonacion) {
        this.iddonacion = iddonacion;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getIdstatusdonacion() {
        return idstatusdonacion;
    }

    public void setIdstatusdonacion(String idstatusdonacion) {
        this.idstatusdonacion = idstatusdonacion;
    }

    public String getNreferencia() {
        return nreferencia;
    }

    public void setNreferencia(String nreferencia) {
        this.nreferencia = nreferencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
