package com.interfaces.daniel.asoguau.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;

/**
 * Created by hanyou on 16/09/15.
 */
public class FragmentoHistorialPeliculas extends Fragment {

    private LinearLayoutManager layoutManager;

    public FragmentoHistorialPeliculas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        RecyclerView recilcador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        recilcador.setLayoutManager(layoutManager);

        AdaptadorHistorialPeliculas adaptador = new AdaptadorHistorialPeliculas();
        recilcador.setAdapter(adaptador);
        recilcador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }


}
