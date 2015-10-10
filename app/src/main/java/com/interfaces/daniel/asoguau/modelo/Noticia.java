package com.interfaces.daniel.asoguau.modelo;

import com.interfaces.daniel.asoguau.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyou on 17/09/15.
 */
public class Noticia {

    public String titulo;
    public String descripcion;
    public int idDrawable;


    public Noticia(String titulo, String descripcion, int idDrawable) {
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
