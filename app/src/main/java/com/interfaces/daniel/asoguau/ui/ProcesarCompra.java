package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.interfaces.daniel.asoguau.R;

public class ProcesarCompra extends AppCompatActivity {

    private static final String EXTRA_NOMBRE = "com.interfaces.daniel.asoguau.nombre";

    public static void createInstance(Activity activity, String titulo) {
        Intent intent = getLaunchIntent(activity, titulo);
        activity.startActivity(intent);
    }

    private static Intent getLaunchIntent(Context context, String titulo) {

        Intent intent = new Intent(context, ProcesarCompra.class);
        intent.putExtra(EXTRA_NOMBRE, titulo);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_compra);

        agregarToolBar();

        Intent i = getIntent();
        TextView tituloPelicula = (TextView) findViewById(R.id.texto_titulo_pelicula);
        String titulo = i.getStringExtra(EXTRA_NOMBRE);
        tituloPelicula.setText(titulo);


    }


    private Activity activity = this;

    private void agregarToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_procesar_compra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
