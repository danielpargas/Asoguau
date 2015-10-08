package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.modelo.Pelicula;

import java.util.List;

/**
 * Created by hanyou on 17/09/15.
 */
public class AdaptadorPeliculas extends RecyclerView.Adapter<AdaptadorPeliculas.ViewHolder> {


    private final List<Pelicula> items;
    private final int indiceSeccion;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView titulo;
        public TextView descripcion;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);

            titulo = (TextView) v.findViewById(R.id.titulo_pelicula);
            descripcion = (TextView) v.findViewById(R.id.descripcion_pelicula);
            imagen = (ImageView) v.findViewById(R.id.miniatura_pelicula);
        }
    }


    public AdaptadorPeliculas(List<Pelicula> items, int indiceSeccion) {
        this.items = items;
        this.indiceSeccion = indiceSeccion;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Activity activity;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_pelicula, viewGroup, false);
        activity = (Activity) viewGroup.getContext();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Pelicula item = items.get(i);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.titulo.setText(item.getNombre());
        viewHolder.descripcion.setText(item.getDescripcion().substring(0, 20) + "...");
        viewHolder.itemView.setId(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Pelicula peliculaActual = null;

                switch (indiceSeccion) {
                    case 0:
                        peliculaActual = Pelicula.TODAS.get(v.getId());
                        break;
                    case 1:
                        peliculaActual = Pelicula.RECIENTES.get(v.getId());
                }

                DetallesPelicula.createInstance(activity, peliculaActual);
            }
        });


    }


}