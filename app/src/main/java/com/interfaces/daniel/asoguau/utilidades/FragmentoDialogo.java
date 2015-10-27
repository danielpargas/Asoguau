package com.interfaces.daniel.asoguau.utilidades;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDialogo extends Fragment {

    private String titulo;
    private String mensaje;
    private String txtBoton;

    public FragmentoDialogo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragmento_dialogo, container, false);

        DialogoOK dialogoOK = new DialogoOK();
        dialogoOK.setTitulo(this.getTitulo());
        dialogoOK.setMensaje(this.getMensaje());
        dialogoOK.setTxtBoton(this.getTxtBoton());

        dialogoOK.show(getFragmentManager(), "DialogoOK");

        return v;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String menssaje) {
        this.mensaje = menssaje;
    }

    public String getTxtBoton() {
        return txtBoton;
    }

    public void setTxtBoton(String txtBoton) {
        this.txtBoton = txtBoton;
    }
}
