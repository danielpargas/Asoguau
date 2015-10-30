package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;


public class FragmentoPerfil extends Fragment {

    public FragmentoPerfil() {
        // Required empty public constructor
    }

    private FloatingActionButton editar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_perfil, container, false);

        editar = (FloatingActionButton) view.findViewById(R.id.fab_perfil);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActualizarPerfil.class);
                startActivity(intent);
            }
        });

        Activity activity = getActivity();
        SharedPreferences preferences = activity.getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);

        ImageView imagen = (ImageView) view.findViewById(R.id.image_paralax_perfil);

        Glide.with(getActivity())
                .load(VolleyAPI.URL_CARPETA_IMAGENES_USUARIOS + "/" + preferences.getString("idusuario", "0") + ".jpg")
                .placeholder(R.drawable.perfil)
                .error(R.drawable.perfil)
                .crossFade()
                .into(imagen);

/*
        VolleyAPI.getInstance(getActivity()).addToRequestQueue(new ImageRequest(
                VolleyAPI.URL_CARPETA_IMAGENES_USUARIOS + "/" + preferences.getString("idusuario", "0") + ".jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        byte[] bytes = ProcesarImagen.bitmapToArrayBytes(response);

                        Glide.with(getActivity())
                                .load(bytes)
                                .centerCrop()
                                .into(imagen);
                    }
                },
                0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Glide.with(getActivity())
                                .load(R.drawable.perfil)
                                .centerCrop()
                                .into(imagen);
                    }
                }
        ));
*/

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_perfil);
        toolbar.setTitle(preferences.getString("nombre", "Usuario") + " " + preferences.getString("apellido", ""));

        TextView txtCorreo = (TextView) view.findViewById(R.id.texto_correo);
        TextView txtTelefono = (TextView) view.findViewById(R.id.texto_telefono);

        txtCorreo.setText(preferences.getString("correo", "N/A"));
        txtTelefono.setText(preferences.getString("telefono", "N/A"));

        // Inflate the layout for this fragment
        return view;
    }

}
