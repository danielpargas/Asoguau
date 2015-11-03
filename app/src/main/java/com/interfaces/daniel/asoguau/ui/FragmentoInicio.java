package com.interfaces.daniel.asoguau.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.interfaces.daniel.asoguau.utilidades.DialogoCarga;

import java.util.HashMap;
import java.util.Map;

public class FragmentoInicio extends Fragment {

    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    private DialogoCarga dialogoCarga;


    public FragmentoInicio() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        dialogoCarga = new DialogoCarga(getActivity());

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        dialogoCarga.mostrarDialogo("Cargando Noticias");

        Map<String, String> parametros = new HashMap<String, String>();
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

                                adaptador = new AdaptadorInicio(response.getItems(), getActivity());
                                reciclador.setAdapter(adaptador);
                                dialogoCarga.ocultarDialogo();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialogoCarga.ocultarDialogo();
                                Log.d("ERROR NOTICIAS", error.getMessage());
                            }
                        },
                        parametros
                )
        );

        return view;

    }


}
