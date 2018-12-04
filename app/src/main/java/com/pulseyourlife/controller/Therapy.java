package com.pulseyourlife.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pulseyourlife.R;

import java.util.HashMap;

public class Therapy extends AppCompatActivity {
    private HashMap<String, String> therapys;
    private TextView textV ;
    private ImageView imageV;
    private int nThe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);
        nThe= 1;
        textV  = (TextView) findViewById(R.id.textTherapy);
        imageV  = (ImageView) findViewById(R.id.imageThe);
        setToolbarBackButton();
        generateInfo();
        setTherapy();

    }
    private void generateInfo(){
        therapys = new HashMap<>();
        therapys.put("low1", "En vez de sentarte en una silla normal, siéntante sobre un balón deportivo. Esto hace que tus músculos trabajen con más fuerza para mantenerte erguido y en equilibrio. También puedes olvidarte de sentarte y permanecer de pie todo lo que puedas a lo largo del día. Estos pequeños cambios pueden afectar a tu corazón.");
        therapys.put("low2", "Caminar es una buena manera de aumentar el ritmo cardíaco. ¡No hace falta que camines rápido! Una velocidad normal aumentará tu ritmo cardíaco y ejercitará tu cuerpo.");
        therapys.put("low3", "Haz algunos estiramientos normales después de moverte un poco para mantener tu ritmo cardíaco ligeramente por encima del ritmo en reposo.");
        therapys.put("medium", "Tienes un ritmo cardiaco estable");
        therapys.put("high1", "Estar de pie y agacharse un poco apoyando las manos en las piernas y toser con fuerza 5 veces seguidas.");
        therapys.put("high2", "Respirar profundamente y soltar el aire lentamente por la boca, como si estuviera apagando suavemente una vela.");
        therapys.put("high3", "Hacer una cuenta atrás de 10 a 0 dos veces, tratando de calmarse y regular la respiracion.");

    }
    private void setTherapy(){
        int promPulse = 0;
        if(promPulse > 40 && promPulse < 100){
            textV.setText(therapys.get("medium"));
            imageV.setImageResource(R.drawable.boton_redondo);

        }else if(promPulse > 100){
            if(nThe == 1){
                textV.setText(therapys.get("high1"));
                //imageV.setImageResource(R.drawable.alto1);
                nThe++;
            }else if(nThe == 2){
                textV.setText(therapys.get("high2"));
                nThe++;
            }else {
                textV.setText(therapys.get("high3"));
                //BOTON DESHAB
            }

        }else if(promPulse ==0){
            AlertDialog alertDialog = new AlertDialog.Builder(Therapy.this).create();
            alertDialog.setTitle("C MURIO");
            alertDialog.setMessage("C MURIO X2");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            imageV.setImageResource(R.drawable.boton_redondo);
        }else{
            if(nThe == 1){
                textV.setText(therapys.get("low1"));
                nThe++;
            }else if(nThe == 2){
                textV.setText(therapys.get("low2"));
                nThe++;
            }else {
                textV.setText(therapys.get("low3"));
                //BOTON DESHAB
            }
            imageV.setImageResource(R.drawable.boton_redondo);
        }
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
