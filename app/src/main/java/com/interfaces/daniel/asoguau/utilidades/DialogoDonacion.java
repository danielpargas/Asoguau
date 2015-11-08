package com.interfaces.daniel.asoguau.utilidades;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanyou on 07/11/15.
 */
public class DialogoDonacion extends DialogFragment {

    private static final String TAG = DialogoDonacion.class.getSimpleName();

    public DialogoDonacion() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDonacionDialogo();
    }

    private EditText monto;

    DialogoCarga dialogoCarga;

    private AlertDialog createDonacionDialogo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_donacion, null);

        builder.setView(v);

        Button donar = (Button) v.findViewById(R.id.donar);
        Button salir = (Button) v.findViewById(R.id.salir);

        monto = (EditText) v.findViewById(R.id.monto);

        donar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean error = false;

                        if (monto.getText().toString().isEmpty()) {
                            monto.setError("Ingrese un Monto");
                            error = true;
                        }
                        if (!error) {

                            dialogoCarga = new DialogoCarga(getActivity());
                            dialogoCarga.mostrarDialogo("Enviando Donacion");

                            VolleyAPI.getInstance(getActivity()).addToRequestQueue(new MiJsonObjectRequest(
                                    Request.Method.POST,
                                    VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_SUBIR_DONACION,
                                    null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            dialogoCarga.ocultarDialogo();

                                            try {
                                                if (response.getString("estatus").equals(0)) {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(getActivity().getBaseContext(), "Ocurrio un Error al Hacer la Donacion", Toast.LENGTH_LONG).show();
                                                        }
                                                    }).run();
                                                } else {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(getActivity().getBaseContext(), "Gracias por hacer tu donacion", Toast.LENGTH_LONG).show();
                                                        }
                                                    }).run();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                            dismiss();
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            dialogoCarga.ocultarDialogo();
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getActivity().getBaseContext(), "Error al hacer la Donacion", Toast.LENGTH_LONG).show();
                                                }
                                            }).run();
                                            dismiss();
                                        }
                                    }
                            ) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {

                                    Map<String, String> parametros = new HashMap<String, String>();

                                    SharedPreferences preferences = getActivity().getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);

                                    parametros.put("idusuario", preferences.getString("idusuario", String.valueOf(-1)));
                                    parametros.put("monto", monto.getText().toString());
                                    return parametros;
                                }
                            });
                        }


                        //dismiss();
                    }
                }
        );

        salir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }

        );

        return builder.create();
    }

}