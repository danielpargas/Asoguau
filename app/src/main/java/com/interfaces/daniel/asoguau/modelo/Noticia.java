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
        NOTICIAS.add(new Noticia("Hacemos Ruido en todos lado", "Los mejores estrenos los tenemos", R.drawable.noticia_cine));
        NOTICIAS.add(new Noticia("Hecho en Venezuela", "Ven a ver lo mejor de nuestro cine", R.drawable.noticia_cine_ve));
        NOTICIAS.add(new Noticia("¿Provocativo? ;)", "Disfruta tus cotufas con nuestras pelliculas", R.drawable.noticia_cotufa));
        NOTICIAS.add(new Noticia("¿Tienes alguna idea?", "Participa en nuestro festival de Cine", R.drawable.noticia_evento));
        NOTICIAS.add(new Noticia("Tecnologia de ultima generacion", "Te sentiras dentro de la pelicula", R.drawable.noticia_calidad));
        NOTICIAS.add(new Noticia("Prueba nuestras instalaciones", "Maxima comodidad y calidad", R.drawable.noticia_comodidad));
        NOTICIAS.add(new Noticia("Pasate por nuestras redes", "No te pierdas nuestros eventos y concursos", R.drawable.noticias_redes));
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
