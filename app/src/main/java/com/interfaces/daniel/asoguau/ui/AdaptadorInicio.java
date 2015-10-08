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
import com.interfaces.daniel.asoguau.modelo.Noticia;

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

    public AdaptadorInicio() {
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
        Noticia item = Noticia.NOTICIAS.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(holder.imagen);

        holder.nombre.setText(item.getTitulo());
        holder.desripcion.setText(item.getDescripcion());
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
        return Noticia.NOTICIAS.size();
    }
}
