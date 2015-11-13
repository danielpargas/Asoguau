package com.interfaces.daniel.asoguau.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.interfaces.daniel.asoguau.R;

import java.util.Locale;

public class ActivityCambioIdioma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_cambio_idioma);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.contenedor_configuracion, new FragmentoConfiguracion());
        transaction.commit();

        agregarToolbar();

    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();


        //myListPref.setText(myListPreference);
    }


    public static class FragmentoConfiguracion extends PreferenceFragment {

        public FragmentoConfiguracion() {
            // Constructor Por Defecto
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferencias);

            final ListPreference prefListIdioma = (ListPreference) findPreference("lista_idiomas");
            prefListIdioma.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    //SharedPreferences myPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    int myListPreference = Integer.valueOf(newValue.toString());


                    Configuration config = new Configuration();

                    switch (myListPreference) {
                        case 0:
                            config.locale = new Locale("es", "");
                            break;
                        case 1:
                            config.locale = Locale.ENGLISH;
                            break;
                        default:
                            config.locale = new Locale("es", "");

                    }
                    getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                    Intent intent = new Intent(getActivity().getApplicationContext(), Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    return true;
                }
            });

        }
    }

}
