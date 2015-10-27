package com.interfaces.daniel.asoguau.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.GsonRequest;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;
import com.interfaces.daniel.asoguau.modelo.Noticias;

import java.util.HashMap;
import java.util.Map;


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
        View view = inflater.inflate(R.layout.fragmento_grupo_items2, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        Map<String, String> parametros = new HashMap<String, String>();

        switch (indiceSeccion) {
            case 0:
                parametros.put("idtiponoticia", String.valueOf(1));
                VolleyAPI.getInstance(getActivity().getBaseContext()).addToRequestQueue(
                        new GsonRequest<Noticias>(
                                Request.Method.POST,
                                VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_NOTICIAS,
                                Noticias.class,
                                null,
                                new Response.Listener<Noticias>() {
                                    @Override
                                    public void onResponse(Noticias response) {
                                        adaptador = new AdaptadorPeliculas(response.getItems(), 0);
                                        reciclador.setAdapter(adaptador);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                },
                                parametros

                        ));
                //adaptador = new AdaptadorPeliculas(Pelicula.TODAS, indiceSeccion);
                break;
            case 1:
                //adaptador = new AdaptadorPeliculas(Pelicula.RECIENTES, indiceSeccion);
                parametros.put("idtiponoticia", String.valueOf(1));
                parametros.put("recientes", "1");

                VolleyAPI.getInstance(getActivity().getBaseContext()).addToRequestQueue(
                        new GsonRequest<Noticias>(
                                Request.Method.POST,
                                VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_NOTICIAS,
                                Noticias.class,
                                null,
                                new Response.Listener<Noticias>() {
                                    @Override
                                    public void onResponse(Noticias response) {
                                        adaptador = new AdaptadorPeliculas(response.getItems(), 1);
                                        reciclador.setAdapter(adaptador);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                },
                                parametros
                        ));
                break;
        }


        return view;
    }

}