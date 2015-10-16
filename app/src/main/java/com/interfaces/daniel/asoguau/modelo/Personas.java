package com.interfaces.daniel.asoguau.modelo;

import java.io.Serializable;

/**
 * Created by Dulcire on 28-05-2015.
 */
public class Personas implements Serializable {

    private String idusuario;
    private String idtipousuario;
    private String idstatususuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String user;
    private String telefono;


    public String getIdusuario() {
        return idusuario;
    }

    public Personas() {
        this.idusuario = "";
        this.idtipousuario = "";
        this.idstatususuario = "";
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
        this.user = "";
        this.telefono = "";
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(String idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getIdstatususuario() {
        return idstatususuario;
    }

    public void setIdstatususuario(String idstatususuario) {
        this.idstatususuario = idstatususuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
