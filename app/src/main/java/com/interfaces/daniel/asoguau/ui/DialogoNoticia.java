package com.interfaces.daniel.asoguau.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoNoticia extends Fragment {


    public DialogoNoticia() {
        // Required empty public constructor
    }

    /*
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }

    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_close);
        }


    }

    public void show(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        String tag = DialogoNoticia.class.getName();
        transaction
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.contenedor_principal, this, tag)
                .commit();
    }

    public void dimiss() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction
                .remove(this)
                .commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noticia_dialogo, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_nueva_noticia, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                break;
            case R.id.action_send:
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
