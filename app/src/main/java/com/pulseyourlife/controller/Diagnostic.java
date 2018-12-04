package com.pulseyourlife.controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pulseyourlife.R;

public class Diagnostic extends AppCompatActivity{

    private String diagnosticoFin;
    private int pulso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);
        setToolbarBackButton();
        setDiagnostic();

    }

    private void setDiagnostic(){
        diagnosticoFin = "";
        pulso = (int) (Math.random() * 10) + 1;

        /**SharedPreferences prefs = getSharedPreferences("MisPreferencias", getApplicationContext().MODE_PRIVATE);
        Gson gson = new Gson(); //Instancia Gson.
        String json = prefs.getString("diario", "");**/
        final TextView label = (TextView) findViewById(R.id.resDiag);


        if(pulso <4){
            diagnosticoFin = "Tu pulso se encuentra muy bajo, te recomendamos que vayas a la seccion de terapia y actives tu cuerpo"
                                     + ", recuerda que una vida activa y con ejercicio mantiene un corazon sano.";
        }else if(pulso > 6){
            diagnosticoFin = "Tu pulso se encuentra muy elevado, te recomendamos que vayas a la seccion de terapia y regules tu pulso"
                    + ", manten la calma y toma tu tiempo para regular tu pulso correctamente. Estamos para cuidarte";
        }else{
            diagnosticoFin = "Tu pulso se encuentra estable, te recomendamos que continues con tu dia cuidando tu corazon, come sano,"
                    + " haz ejercicio y no olvides estar revisando tu pulso. Lindo día";
        }

        label.setText(diagnosticoFin);
    }

    private void setToolbarBackButton(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }




}
