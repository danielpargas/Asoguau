package com.interfaces.daniel.asoguau.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.modelo.Donacion;

import java.util.List;

/**
 * Created by hanyou on 07/11/15.
 */
public class AdaptadorDonacion extends RecyclerView.Adapter<AdaptadorDonacion.ViewHolder> {


    private List<Donacion> items;
    private Context context;

    public AdaptadorDonacion(List<Donacion> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donacion, parent, false);
        context = v.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Donacion item = items.get(position);


        Log.d("REFERENCIA", item.getNreferencia());

        holder.referencia.setText("#" + item.getNreferencia());
        holder.datos.setText(item.getFecha() + ", " + item.getMonto() + "Bs");

        if (item.getIdstatusdonacion().equals("1")) {
            Glide.with(context)
                    .load(R.drawable.donacion_aceptada)
                    .crossFade(1000)
                    .into(holder.estatus);
        } else if (item.getIdstatusdonacion().equals("2")) {

            Glide.with(context)
                    .load(R.drawable.donacion_rechazada)
                    .crossFade(1000)
                    .into(holder.estatus);
        } else if (item.getIdstatusdonacion().equals("3")) {
            Glide.with(context)
                    .load(R.drawable.donacion_espera)
                    .crossFade(1000)
                    .into(holder.estatus);
        }

    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView referencia;
        private TextView datos;
        private ImageView estatus;

        public ViewHolder(View itemView) {
            super(itemView);

            referencia = (TextView) itemView.findViewById(R.id.referencia);
            datos = (TextView) itemView.findViewById(R.id.datos);
            estatus = (ImageView) itemView.findViewById(R.id.estatus);

        }
    }
}
