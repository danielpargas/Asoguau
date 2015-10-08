package com.interfaces.daniel.asoguau.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDatePicker extends Fragment {


    public FragmentoDatePicker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_date_picker, container, false);

        FragmentManager fragmentManager = getFragmentManager();

        new DateDialog().show(fragmentManager, "Fecha");


        return view;
    }


}
