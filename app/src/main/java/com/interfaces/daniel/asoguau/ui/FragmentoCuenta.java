package com.interfaces.daniel.asoguau.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interfaces.daniel.asoguau.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentoCuenta extends Fragment {

    private AppBarLayout appbar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public FragmentoCuenta() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);


        if (savedInstanceState == null) {
            insertarTabs(container);
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adaptador = new AdaptadorSecciones(getFragmentManager());
        adaptador.addFragment(new FragmentoPerfil(), getString(R.string.titulo_tab_perfil));
        adaptador.addFragment(new FragmentoHistorialPeliculas(), getString(R.string.titulo_tab_noticias));
        adaptador.addFragment(new FragmentoHistorialPeliculas(), getString(R.string.titulo_tab_donacion));


        viewPager.setAdapter(adaptador);

    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appbar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appbar.addView(pestanas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appbar.removeView(pestanas);
    }


    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }


}
