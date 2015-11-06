package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;
import com.interfaces.daniel.asoguau.modelo.Noticia;
import com.interfaces.daniel.asoguau.modelo.Pelicula;
import com.interfaces.daniel.asoguau.utilidades.ProcesarImagen;

import java.util.List;

/**
 * Created by hanyou on 17/09/15.
 */
public class AdaptadorPeliculas extends RecyclerView.Adapter<AdaptadorPeliculas.ViewHolder> {


    private final List<Noticia> items;
    private final int indiceSeccion;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView titulo;
        public TextView descripcion;
        public TextView fecha;
        public ImageView imagen;
        public ImageView imagenUsuario;

        public ViewHolder(View v) {
            super(v);

            titulo = (TextView) v.findViewById(R.id.titulo_pelicula);
            descripcion = (TextView) v.findViewById(R.id.descripcion_pelicula);
            fecha = (TextView) v.findViewById(R.id.Fecha_Noticia);
            imagen = (ImageView) v.findViewById(R.id.miniatura_pelicula);
            imagenUsuario = (ImageView) v.findViewById(R.id.avatar2);
        }
    }


    public AdaptadorPeliculas(List<Noticia> items, int indiceSeccion) {
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
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Noticia item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(R.mipmap.img_load)
                .centerCrop()
                .into(viewHolder.imagen);

        VolleyAPI.getInstance(viewHolder.itemView.getContext()).addToRequestQueue(new ImageRequest(
                        VolleyAPI.URL_CARPETA_IMAGENES_NOTICIAS + "/" + String.valueOf(item.getIdnoticia()) + ".jpg",
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                byte[] bytes = ProcesarImagen.bitmapToArrayBytes(response);
                                Glide.with(viewHolder.itemView.getContext())
                                        .load(bytes)
                                        .centerCrop()
                                        .into(viewHolder.imagen);
                            }
                        },
                        0, 0, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Glide.with(viewHolder.itemView.getContext())
                                        .load(R.mipmap.img_load)
                                        .centerCrop()
                                        .into(viewHolder.imagen);
                            }
                        }
                )

        );

        Glide.with(activity)
                .load(VolleyAPI.URL_CARPETA_IMAGENES_USUARIOS + "/" + item.getIdusuario() + ".jpg")
                .placeholder(R.drawable.perfil)
                .error(R.drawable.perfil)
                .dontAnimate()
                .into(viewHolder.imagenUsuario);

        viewHolder.titulo.setText(item.getTitulo());
        if (item.getDescripcion().length() > 20) {
            viewHolder.descripcion.setText(item.getDescripcion().substring(0, 20) + "...");
        } else {
            viewHolder.descripcion.setText(item.getDescripcion());
        }
        viewHolder.fecha.setText(item.getFecha());

        viewHolder.itemView.setId(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Pelicula peliculaActual = null;
                Noticia noticiaActual = null;

                switch (indiceSeccion) {
                    case 0:
                        noticiaActual = items.get(v.getId());
                        break;
                    case 1:
                        noticiaActual = items.get(v.getId());
                }

                DetallesPelicula.createInstance(activity, noticiaActual);
            }
        });


    }


}