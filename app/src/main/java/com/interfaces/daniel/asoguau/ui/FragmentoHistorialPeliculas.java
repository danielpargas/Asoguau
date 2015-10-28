package com.interfaces.daniel.asoguau.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;

/**
 * Created by hanyou on 16/09/15.
 */
public class FragmentoHistorialPeliculas extends Fragment implements View.OnClickListener {

    private LinearLayoutManager layoutManager;

    public FragmentoHistorialPeliculas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        RecyclerView recilcador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        recilcador.setLayoutManager(layoutManager);

        AdaptadorHistorialPeliculas adaptador = new AdaptadorHistorialPeliculas();
        recilcador.setAdapter(adaptador);
        recilcador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        FloatingActionButton agregar = (FloatingActionButton) view.findViewById(R.id.mas);
        agregar.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        DialogoNoticia noticia = new DialogoNoticia();

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, noticia, "DialogoNoticia")
                .addToBackStack(null)
                .commit();
    }
}
