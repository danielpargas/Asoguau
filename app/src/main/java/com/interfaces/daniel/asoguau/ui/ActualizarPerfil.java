package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.utilidades.DialogoOK;

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
            DialogoOK dialogoOK = new DialogoOK();

            dialogoOK.setTitulo("Perfil");
            dialogoOK.setMensaje("Perfil Actualizado con Exito");
            dialogoOK.setTxtBoton("Entendido");

            dialogoOK.show(getSupportFragmentManager(), DialogoOK.class.getName());
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
