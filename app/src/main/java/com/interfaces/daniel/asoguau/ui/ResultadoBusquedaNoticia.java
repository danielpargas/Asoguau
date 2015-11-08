package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import java.util.List;

public class ResultadoBusquedaNoticia extends Activity {


    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorPeliculas adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_grupo_items2);

        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(this, 1);
        reciclador.setLayoutManager(layoutManager);

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
            //use the query to search your data somehow
            Log.d("CADENABUSQUEDA", query);
            final DialogoCarga dialogoCarga = new DialogoCarga(this);
            dialogoCarga.mostrarDialogo("Cargando Noticias");

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
                            null//parametros
                    ));


        }


    }

}