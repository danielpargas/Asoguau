package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, View.OnClickListener, DialogoOK.OnSimpleDialogListener {

    private TextView fechaNacimiento;
    private DialogoCarga dialogoCarga;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtTelefono;
    private EditText txtClave;
    private EditText txtClaveConf;
    private EditText txtCorreo;
    private Button btnRegistro;

    /**
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        agregarToolBar();

        txtNombre = (EditText) findViewById(R.id.texto_nombre);
        txtApellido = (EditText) findViewById(R.id.texto_apellido);
        txtTelefono = (EditText) findViewById(R.id.texto_phone);
        txtClave = (EditText) findViewById(R.id.texto_clave);
        txtClaveConf = (EditText) findViewById(R.id.texto_claveconfir);
        txtCorreo = (EditText) findViewById(R.id.texto_mail);

        btnRegistro = (Button) findViewById(R.id.boton_ingreso);
        btnRegistro.setOnClickListener(this);


        /*fechaNacimiento = (TextView) findViewById(R.id.texto_fecha_nacimiento);
        fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentoDatePicker datePicker = new FragmentoDatePicker();

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment, datePicker, "Fragmento")
                        .commit();
            }
        });*/
    }

    private Activity activity = this;

    /**
     * Agregar ToolBar a la Activity actual
     */
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
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        fechaNacimiento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

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

    private String nombre;
    private String apellido;
    private String telefono;
    private String clave;
    private String correo;


    @Override
    public void onClick(final View v) {

        dialogoCarga = new DialogoCarga(this);


        nombre = txtNombre.getText().toString();
        apellido = txtApellido.getText().toString();
        telefono = txtTelefono.getText().toString();
        clave = txtClave.getText().toString();
        correo = txtCorreo.getText().toString();

        boolean error = false;

        if (nombre.isEmpty()) {
            txtNombre.setError("Ingrese Nombre");
            error = true;
        }

        if (apellido.isEmpty()) {
            txtApellido.setError("Ingrese Apellido");
            error = true;
        }

        if (telefono.isEmpty()) {
            txtTelefono.setError("Ingrese Telefono");
            error = true;
        }

        if (clave.isEmpty()) {
            txtClave.setError("Ingrese Clave");
            error = true;
        }

        if (correo.isEmpty()) {
            txtCorreo.setError("Ingrese Correo");
            error = true;
        }

        if (!clave.equals(txtClaveConf.getText().toString()) && !error) {
            txtClave.setError("Las Claves deben ser Iguales");
            error = true;
        }

        if (!isEmailValid(correo) && !error) {
            txtCorreo.setError("Correo Invalido");
            error = true;
        }


        if (!error) {

            dialogoCarga.mostrarDialogo("Enviando Datos");

            VolleyAPI.getInstance(this).addToRequestQueue(new MiJsonObjectRequest(
                    Request.Method.POST,
                    VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_REGISTRO,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if (response.getInt("estatus") == 1) {

                                    dialogoCarga.ocultarDialogo();
                                    Intent intent = new Intent(v.getContext(), Login.class);
                                    activity.finish();
                                    startActivity(intent);

                                } else {

                                    dialogoCarga.ocultarDialogo();

                                    FragmentoDialogo dialogoOk = new FragmentoDialogo();
                                    dialogoOk.setTitulo("Registro");
                                    dialogoOk.setMensaje("Usuario Existente. Intente de nuevo con otro Usuario");
                                    dialogoOk.setTxtBoton("Aceptar");

                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager
                                            .beginTransaction()
                                            .add(dialogoOk, "DialogoOk")
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
                            dialogoCarga.ocultarDialogo();
                            Log.d("ERROR REGISTRO", error.getMessage());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();

                    parametros.put("idtipousuario", "5");
                    parametros.put("idstatususuario", "1");
                    parametros.put("nombre", nombre);
                    parametros.put("apellido", apellido);
                    parametros.put("correo", correo);
                    parametros.put("telefono", telefono);
                    parametros.put("clave", clave);

                    return parametros;
                }
            });


        }


    }

    @Override
    public void onPossitiveButtonClick() {

    }

    @Override
    public void onNegativeButtonClick() {

    }
}
