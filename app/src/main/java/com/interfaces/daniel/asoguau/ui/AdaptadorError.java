package com.interfaces.daniel.asoguau.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.modelo.*;

import java.util.List;

/**
 * Created by hanyou on 05/11/15.
 */
public class AdaptadorError extends RecyclerView.Adapter<AdaptadorError.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView mensaje;

        public ViewHolder(View v) {
            super(v);

            mensaje = (TextView) v.findViewById(R.id.mensaje);
        }
    }


    private List<Errores> items;
    private Context context;


    public AdaptadorError(List<Errores> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_error, viewGroup, false);
        context = v.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Errores item = items.get(i);
        viewHolder.mensaje.setText(item.getMensaje());

    }

}