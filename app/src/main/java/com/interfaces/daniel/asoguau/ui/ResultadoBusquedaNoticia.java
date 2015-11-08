package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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

public class ResultadoBusquedaNoticia extends AppCompatActivity {


    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorPeliculas adaptador;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busqueda_noticia);

        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(this, 1);
        reciclador.setLayoutManager(layoutManager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            ActionBar bar = getSupportActionBar();

            if (bar != null) {
                bar.setTitle(query);
            }
            //use the query to search your data somehow
            Log.d("CADENABUSQUEDA", query);
            final DialogoCarga dialogoCarga = new DialogoCarga(this);
            dialogoCarga.mostrarDialogo("Cargando Noticias");

            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("busqueda", query);
            parametros.put("idtiponoticia", String.valueOf(2));

            VolleyAPI.getInstance(this).addToRequestQueue(
                    new GsonRequest<Noticias>(
                            Request.Method.POST,
                            VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_NOTICIAS,
                            Noticias.class,
                            null,
                            new Response.Listener<Noticias>() {
                                @Override
                                public void onResponse(Noticias response) {
                                    if (response != null && response.getItems().size() > 0) {
                                        adaptador = new AdaptadorPeliculas(response.getItems(), 1);
                                        reciclador.setAdapter(adaptador);
                                    } else {
                                        List<Errores> errores = new ArrayList<Errores>();
                                        errores.add(new Errores("No hay Publicaciones Disponibles"));
                                        AdaptadorError error = new AdaptadorError(errores, getBaseContext());
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

}