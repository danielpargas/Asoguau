package com.interfaces.daniel.asoguau.modelo;

import com.interfaces.daniel.asoguau.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyou on 17/09/15.
 */
public class Noticia {

    public int idnoticia;
    public int idtiponoticia;
    public int idusuario;
    public String titulo;
    public String descripcion;
    public String resumen;
    public String fecha;
    public String hora;
    public int idDrawable;


    public Noticia(String titulo, String descripcion, int idDrawable) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idDrawable = idDrawable;
    }

    public Noticia(int idnoticia, int idtiponoticia, int idusuario, String titulo, String descripcion, String resumen, String fecha, String hora, int idDrawable) {
        this.idnoticia = idnoticia;
        this.idtiponoticia = idtiponoticia;
        this.idusuario = idusuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.resumen = resumen;
        this.fecha = fecha;
        this.hora = hora;
        this.idDrawable = idDrawable;
    }

    public static List<Noticia> NOTICIAS = new ArrayList<>();

    static {
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticia_cine));
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticia_cine_ve));
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticia_cotufa));
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticia_evento));
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticia_calidad));
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticia_comodidad));
        NOTICIAS.add(new Noticia("Titulo Noticia", "Descripcion", R.drawable.noticias_redes));
    }


    public int getIdnoticia() {
        return idnoticia;
    }

    public void setIdnoticia(int idnoticia) {
        this.idnoticia = idnoticia;
    }

    public int getIdtiponoticia() {
        return idtiponoticia;
    }

    public void setIdtiponoticia(int idtiponoticia) {
        this.idtiponoticia = idtiponoticia;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
