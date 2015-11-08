package com.interfaces.daniel.asoguau.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.interfaces.daniel.asoguau.modelo.Donacion;
import com.interfaces.daniel.asoguau.modelo.Errores;
import com.interfaces.daniel.asoguau.modelo.ListaDonacion;
import com.interfaces.daniel.asoguau.modelo.Noticias;
import com.interfaces.daniel.asoguau.utilidades.DialogoCarga;
import com.interfaces.daniel.asoguau.utilidades.DialogoDonacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentoContacto extends Fragment {


    public FragmentoContacto() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private FloatingActionButton mas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        reciclador.setLayoutManager(layoutManager);

        mas = (FloatingActionButton) view.findViewById(R.id.mas);
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                new DialogoDonacion().show(manager, DialogoDonacion.class.getSimpleName());
            }
        });

        final DialogoCarga dialogoCarga = new DialogoCarga(getActivity());
        dialogoCarga.mostrarDialogo("Cargando Donaciones");

        Map<String, String> parametros = new HashMap<String, String>();

        final SharedPreferences preferences = getActivity().getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);

        parametros.put("idusuario", preferences.getString("idusuario", String.valueOf(0)));

        VolleyAPI.getInstance(getActivity().getBaseContext()).addToRequestQueue(
                new GsonRequest<ListaDonacion>(
                        Request.Method.POST,
                        VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_OBTENER_DONACIONES,
                        ListaDonacion.class,
                        null,
                        new Response.Listener<ListaDonacion>() {
                            @Override
                            public void onResponse(ListaDonacion response) {

                                if (response != null && response.getItems().size() > 0) {

                                    AdaptadorDonacion adaptador = new AdaptadorDonacion(response.getItems(), getActivity());
                                    reciclador.setAdapter(adaptador);
                                    reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
                                } else {
                                    List<Errores> errores = new ArrayList<Errores>();
                                    errores.add(new Errores("No ha hecho donaciones"));
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
        return view;
    }
}
