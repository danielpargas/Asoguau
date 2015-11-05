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
import com.interfaces.daniel.asoguau.modelo.Errores;
import com.interfaces.daniel.asoguau.modelo.Noticias;
import com.interfaces.daniel.asoguau.utilidades.DialogoCarga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentoPelicula extends Fragment {

    public FragmentoPelicula() {
        // Required empty public constructor
    }

    private static final String INDICE_SECCION
            = "com.asoguau.FragmentoCategoriasTab.extra.INDICE_SECCION";

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
                parametros.put("idtiponoticia", String.valueOf(2));
                parametros.put("recientes", "1");

                noticias(parametros, indiceSeccion);
                //adaptador = new AdaptadorPeliculas(Pelicula.TODAS, indiceSeccion);
                break;
            case 1:
                parametros.put("idtiponoticia", String.valueOf(2));
                noticias(parametros, indiceSeccion);
                //adaptador = new AdaptadorPeliculas(Pelicula.RECIENTES, indiceSeccion);

                break;
        }
        return view;
    }


    public void noticias(Map<String, String> parametros, final int posicion) {

        final DialogoCarga dialogoCarga = new DialogoCarga(getActivity());
        dialogoCarga.mostrarDialogo("Cargando Noticias");

        VolleyAPI.getInstance(getActivity().getBaseContext()).addToRequestQueue(
                new GsonRequest<Noticias>(
                        Request.Method.POST,
                        VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_NOTICIAS,
                        Noticias.class,
                        null,
                        new Response.Listener<Noticias>() {
                            @Override
                            public void onResponse(Noticias response) {
                                if (response != null && response.getItems().size() > 0) {
                                    adaptador = new AdaptadorPeliculas(response.getItems(), posicion);
                                    reciclador.setAdapter(adaptador);
                                } else {
                                    List<Errores> errores = new ArrayList<Errores>();
                                    errores.add(new Errores("No hay Publicaciones Disponibles"));
                                    AdaptadorError error = new AdaptadorError(errores, getActivity());
                                    reciclador.setAdapter(error);
                                }
                                dialogoCarga.ocultarDialogo();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialogoCarga.ocultarDialogo();
                            }
                        },
                        parametros
                ));
    }

}