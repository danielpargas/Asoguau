package com.interfaces.daniel.asoguau.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.modelo.Pelicula;


public class FragmentoPelicula extends Fragment {

    public FragmentoPelicula() {
        // Required empty public constructor
    }

    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorPeliculas adaptador;

    public static FragmentoPelicula nuevaInstancia(int indiceSeccion) {
        FragmentoPelicula fragment = new FragmentoPelicula();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorPeliculas(Pelicula.TODAS, indiceSeccion);
                break;
            case 1:
                adaptador = new AdaptadorPeliculas(Pelicula.RECIENTES, indiceSeccion);
                break;
        }

        reciclador.setAdapter(adaptador);

        return view;
    }

}