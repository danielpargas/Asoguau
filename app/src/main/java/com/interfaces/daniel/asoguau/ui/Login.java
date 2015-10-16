package com.interfaces.daniel.asoguau.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.MiJsonObjectRequest;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;
import com.interfaces.daniel.asoguau.utilidades.DialogoCarga;
import com.interfaces.daniel.asoguau.utilidades.DialogoOK;
import com.interfaces.daniel.asoguau.utilidades.FragmentoDialogo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements DialogoOK.OnSimpleDialogListener {

    private DialogoCarga dialogo = new DialogoCarga(this);
    private Context context = this;

    private EditText correo;
    private EditText clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = (EditText) findViewById(R.id.user);
        clave = (EditText) findViewById(R.id.pass);

        configurarBotonIngreso();
        configurarBotonRegistro();

    }

    private void configurarBotonRegistro() {
        Button btnRegistro = (Button) findViewById(R.id.boton_registro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registro.class);
                startActivity(intent);
            }
        });

    }

    public void configurarBotonIngreso() {
        Button btnIngreso = (Button) findViewById(R.id.boton_ingreso);
        ImageView imgLogo = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.asoguaulogo2)
                .into(imgLogo);

        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialogo.mostrarDialogo("Comprobando Datos");

                VolleyAPI.getInstance(v.getContext()).addToRequestQueue(new MiJsonObjectRequest(
                        Request.Method.POST,
                        VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_PROCESAR_LOGIN,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    if (response.getInt("estatus") == 1) {

                                        SharedPreferences preferences = getSharedPreferences("DatosUsuario", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();

                                        editor.putString("idusuario", response.getString("idusuario"));
                                        editor.putString("idtipousuario", response.getString("idtipousuario"));
                                        editor.putString("idstatususuario", response.getString("idstatususuario"));
                                        editor.putString("nombre", response.getString("nombre"));
                                        editor.putString("apellido", response.getString("apellido"));
                                        editor.putString("correo", response.getString("correo"));
                                        editor.putString("telefono", response.getString("telefono"));

                                        editor.putBoolean("login", true);

                                        editor.commit();

                                        dialogo.ocultarDialogo();

                                        Intent intent = new Intent(context, ActividadPrincipal.class);
                                        startActivity(intent);


                                    } else {

                                        dialogo.ocultarDialogo();

                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .add(new FragmentoDialogo(), "FragmentoDialogo")
                                                .commit();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialogo.ocultarDialogo();
                                Log.d("ERROR JSON", error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();

                        parametros.put("correo", correo.getText().toString());
                        parametros.put("clave", clave.getText().toString());

                        return parametros;

                    }
                });


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onPossitiveButtonClick() {

    }

    @Override
    public void onNegativeButtonClick() {

    }
}
