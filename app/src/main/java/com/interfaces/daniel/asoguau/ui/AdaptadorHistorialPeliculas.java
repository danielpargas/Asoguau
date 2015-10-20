package com.interfaces.daniel.asoguau.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador para poblar la lista de noticas de la sección "Mi Cuenta"
 */
public class AdaptadorHistorialPeliculas
        extends RecyclerView.Adapter<AdaptadorHistorialPeliculas.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView titulo;
        public TextView nroReferencia;
        public TextView horario;
        public TextView fechaCompra;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            titulo = (TextView) v.findViewById(R.id.texto_titulo_pelicula);
            nroReferencia = (TextView) v.findViewById(R.id.texto_nro_referencia);
            horario = (TextView) v.findViewById(R.id.texto_horario);
            fechaCompra = (TextView) v.findViewById(R.id.texto_fecha_compra);
            imagen = (ImageView) v.findViewById(R.id.miniatura_pelicula);
        }
    }


    public AdaptadorHistorialPeliculas() {
    }

    @Override
    public int getItemCount() {
        return HistorialCompra.HISTORIAL.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_historial_peliculas, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        HistorialCompra item = HistorialCompra.HISTORIAL.get(i);
        viewHolder.titulo.setText(item.getTitulo());
        viewHolder.nroReferencia.setText(item.getReferencia());
        viewHolder.horario.setText(item.getHorario());
        viewHolder.fechaCompra.setText(item.getFechaCompra());

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .into(viewHolder.imagen);
    }

    /**
     * Modelo de datos para probar el adaptador
     */

    public static class HistorialCompra {
        public String titulo;
        public String referencia;
        public String horario;
        public String fechaCompra;
        public int idDrawable;


        public HistorialCompra(String titulo, String referencia, String horario, String fechaCompra, int idDrawable) {
            this.titulo = titulo;
            this.referencia = referencia;
            this.horario = horario;
            this.fechaCompra = fechaCompra;
            this.idDrawable = idDrawable;
        }

        public static List<HistorialCompra> HISTORIAL = new ArrayList<>();

        static {
            HISTORIAL.add(new HistorialCompra("Mi Noticia 1", "", "Descripcion de noticia Descripcion de noticia", "17/09/2015", R.drawable.noticia_cine));
            HISTORIAL.add(new HistorialCompra("Mi Noticia 2", "", "Descripcion de noticia", "17/09/2015", R.drawable.noticia_comodidad));
            HISTORIAL.add(new HistorialCompra("Mi Noticia 3", "", "Descripcion de noticia Descripcion de noticia", "17/09/2015", R.drawable.noticia_cine));
            HISTORIAL.add(new HistorialCompra("Mi Noticia 4", "", "Descripcion de noticia", "17/09/2015", R.drawable.noticia_comodidad));
            HISTORIAL.add(new HistorialCompra("Mi Noticia 5", "", "Descripcion de noticia Descripcion de noticia", "17/09/2015", R.drawable.noticia_cine));
        }

        public String getTitulo() {
            return titulo;
        }

        public String getReferencia() {
            return referencia;
        }

        public String getHorario() {
            return horario;
        }

        public String getFechaCompra() {
            return fechaCompra;
        }

        public int getIdDrawable() {
            return idDrawable;
        }
    }


}
