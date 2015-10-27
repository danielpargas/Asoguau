package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.content.Context;
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
import com.interfaces.daniel.asoguau.utilidades.ProcesarImagen;

import java.util.List;

/**
 * Created by hanyou on 14/09/15.
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView desripcion;
        public ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.titulo_noticia);
            desripcion = (TextView) itemView.findViewById(R.id.descripcion_noticia);
            imagen = (ImageView) itemView.findViewById(R.id.miniatura_pelicula);
        }
    }

    private List<Noticia> items;
    private Context context;


    public AdaptadorInicio() {
    }

    public AdaptadorInicio(List<Noticia> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public Activity activity;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_inicio, parent, false);
        activity = (Activity) parent.getContext();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        /**
         * Carga de Imagenes
         */
        VolleyAPI.getInstance(context).addToRequestQueue(new ImageRequest(
                VolleyAPI.URL_CARPETA_IMAGENES_NOTICIAS + "/" + String.valueOf(position + 1) + ".jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        //Conversion del Bitmap en Byte Array

                        byte[] bytes = ProcesarImagen.bitmapToArrayBytes(response);

                        Glide.with(holder.itemView.getContext())
                                .load(bytes)
                                .centerCrop()
                                .into(holder.imagen);

                    }
                },
                0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Glide.with(holder.itemView.getContext())
                                .load(R.drawable.noticias_redes)
                                .centerCrop()
                                .into(holder.imagen);

                    }
                }
        ));

/*
        Glide.with(holder.itemView.getContext())
                .load(R.drawable.noticias_redes)
                .centerCrop()
                .into(holder.imagen);
*/
        holder.nombre.setText(items.get(position).getTitulo());
        holder.desripcion.setText(items.get(position).getResumen());

        /*
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView desripcion = (TextView)v.findViewById(R.id.precio_comida);
                DetallesPelicula.createInstance(activity, Pelicula.TODAS.get(1));
                Log.e("HOLA", "Me costo: " + desripcion.getText() + " $");
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
