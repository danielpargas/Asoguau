package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.MiJsonObjectRequest;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;
import com.interfaces.daniel.asoguau.utilidades.DialogoOK;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActualizarPerfil extends AppCompatActivity implements View.OnClickListener, DialogoOK.OnSimpleDialogListener {

    private Activity activity = this;
    private EditText nombre;
    private EditText apellido;
    private EditText telefono;
    private EditText correo;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actualizar_perfil);
        agregarToolbar();

        nombre = (EditText) findViewById(R.id.texto_nombre);
        apellido = (EditText) findViewById(R.id.texto_apellido);
        telefono = (EditText) findViewById(R.id.texto_phone);
        correo = (EditText) findViewById(R.id.texto_mail);
        guardar = (Button) findViewById(R.id.boton_guardar);

        SharedPreferences preferences = getSharedPreferences("DatosUsuario", MODE_PRIVATE);

        nombre.setText(preferences.getString("nombre", "nombre"));
        apellido.setText(preferences.getString("apellido", "apellido"));
        telefono.setText(preferences.getString("telefono", "telefono"));
        correo.setText(preferences.getString("correo", "correo"));

        guardar.setOnClickListener(this);
    }


    public void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        boolean error = false;

        if (nombre.getText().toString().isEmpty()) {
            nombre.setError("Ingrese Nombre");
            error = true;
        }

        if (apellido.getText().toString().isEmpty()) {
            apellido.setError("Ingrese Apellido");
            error = true;
        }

        if (telefono.getText().toString().isEmpty()) {
            telefono.setError("Ingrese Telefono");
            error = true;
        }

        if (correo.getText().toString().isEmpty()) {
            correo.setError("Ingrese Correo");
            error = true;
        }

        if (!isEmailValid(correo.getText().toString()) && !error) {
            correo.setError("¡Correo Invalido!");
            error = true;
        }

        if (!error) {

            VolleyAPI.getInstance(this).addToRequestQueue(new MiJsonObjectRequest(
                    Request.Method.POST,
                    VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_ACTUALIZAR_PERFIL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("USUARIO", response.getString("estatus"))
                                ;
                                if (response.getInt("estatus") == 1) {

                                    SharedPreferences preferences = activity.getSharedPreferences("DatosUsuario", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();

                                    editor.putString("nombre", nombre.getText().toString().trim());
                                    editor.putString("apellido", apellido.getText().toString().trim());
                                    editor.putString("correo", correo.getText().toString().trim());
                                    editor.putString("telefono", telefono.getText().toString().trim());

                                    editor.commit();

                                    DialogoOK dialogoOK = new DialogoOK();

                                    dialogoOK.setTitulo("Perfil");
                                    dialogoOK.setMensaje("Perfil Actualizado con Exito");
                                    dialogoOK.setTxtBoton("Entendido");

                                    dialogoOK.show(getSupportFragmentManager(), DialogoOK.class.getName());
                                } else {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(activity, "Usuario Existente Intente de Nuevo", Toast.LENGTH_LONG).show();
                                        }
                                    }).run();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            DialogoOK dialogoOK = new DialogoOK();

                            dialogoOK.setTitulo("Perfil");
                            dialogoOK.setMensaje("Errores al Actualizar Perfil");
                            dialogoOK.setTxtBoton("Entendido");

                            dialogoOK.show(getSupportFragmentManager(), DialogoOK.class.getName());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();

                    SharedPreferences preferences = activity.getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);

                    parametros.put("idusuario", preferences.getString("idusuario", String.valueOf(0)));
                    parametros.put("nombre", nombre.getText().toString().trim());
                    parametros.put("apellido", apellido.getText().toString().trim());
                    parametros.put("correo", correo.getText().toString().trim());
                    parametros.put("telefono", telefono.getText().toString().trim());

                    return parametros;
                }
            });


        }

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    @Override
    public void onPossitiveButtonClick() {
        this.finish();
    }

    @Override
    public void onNegativeButtonClick() {

    }
}
