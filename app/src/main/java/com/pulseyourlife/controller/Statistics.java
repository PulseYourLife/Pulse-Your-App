package com.pulseyourlife.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.juang.jplot.PlotBarritas;
import com.juang.jplot.PlotPlanitoXY;
import com.pulseyourlife.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.content.SharedPreferences;

public class Statistics extends Fragment {
    private PlotPlanitoXY plot;
    private PlotBarritas ColumnaAgrupada;
    private PlotBarritas ColumnaApilada100;;


    private LinearLayout estadistica;
    private LinearLayout estadistica2;
    private LinearLayout estadistica3;
    private Context context;
    private Button showTherapy;
    public Statistics() {
    }


    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        showTherapy = (Button) getView().findViewById(R.id.bterapia);
        showTherapy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActivity().startActivity(new Intent(getActivity(),Therapy.class));
            }
        });
        //setContentView(R.layout.fragment_statistics);
        context = view.getContext();

        estadistica = (LinearLayout) getView().findViewById(R.id.estadistica);
        estadistica2= (LinearLayout) getView().findViewById(R.id.estadistica2);
        estadistica3= (LinearLayout) getView().findViewById(R.id.estadistica3);

        float x[]= {2,3,4,5};
        float y[]= {4,3,6,9};
        plot = new PlotPlanitoXY( context, "Así va tu pulso hoy","Hoy", "Pulso");
        plot.SetSerie1(x,y,"Ritmo Cardiaco",5,true);
        plot.SetHD(true);
        plot.SetTouch(true);
        estadistica.addView(plot);

        //--------------------------------------------

        String xb[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio"};
        String Acota[]={"Grave","Medio","Normal"};
        double yb[][]={{2 ,3,1},
                {5 ,2,5},
                {1,3,2},
                {0 ,3,1},
                {2 ,4,1},
                {2 ,0,1},
                {2 ,1,1}};

        ColumnaAgrupada = new PlotBarritas(context,"Tu pulso mensual","Pulso");

        ColumnaAgrupada.ColumnaAgrupada(xb,yb,Acota);
        ColumnaAgrupada.SetSizeAcot(15);
        ColumnaAgrupada.SetSizeTitulo(20);
        ColumnaAgrupada.SetSizeTituloY(12);
        ColumnaAgrupada.SetHD(true);
        ColumnaAgrupada.SetColorContorno(253, 254, 254);//contorno en rojo
        ColumnaAgrupada.SetColorPila(1,0, 128, 128);
        ColumnaAgrupada.SetColorPila(2,0, 139, 139);//segunda columna de grupo en rojo
        ColumnaAgrupada.SetColorPila(3,32, 178, 170);
        estadistica2.addView(ColumnaAgrupada);

        //--------------------------------------------

        String xz[]={"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
        String Acot[]={"Grave","Medio","Normal"};
        double yz[][]={{2 ,3,1},//y[7]{3]  defina un array de 7 grupos con 3 columnas  puede ser de y[n][m] con n,m cualquier entero
                {5 ,2,5},
                {1,3,2},
                {0 ,3,1},
                {2 ,4,1},
                {2 ,0,1},
                {2 ,1,1}};

        ColumnaApilada100=new PlotBarritas(context,"Tu pulso por dia","Pulsos por dia");
        //personalizacion de grafico
        ColumnaApilada100.ColumnaApilada100(xz,yz,Acot);
        ColumnaApilada100.SetSizeAcot(15);
        ColumnaApilada100.SetSizeTitulo(20);
        ColumnaApilada100.SetSizeTituloY(12);
        ColumnaApilada100.SetHD(true);
        ColumnaApilada100.SetContorno(0);
        ColumnaApilada100.SetColorPila(1,0, 128, 128);//primera pila de columna de color hot pink
        ColumnaApilada100.SetColorPila(2,0, 139, 139);//segunda pila de columna de color amarillo
        ColumnaApilada100.SetColorPila(3,32, 178, 170);//segunda pila de columna de color amarillo
        estadistica3.addView(ColumnaApilada100);

        //-------------------------------------------
        SharedPreferences preferences = this.getActivity().getSharedPreferences("datosEstadistica", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();  //Instancia Gson.
        String dia = gson.toJson(y);
        String semana = gson.toJson(yz);
        String mes = gson.toJson(yb);
        editor.putString("diario", dia);
        editor.putString("semanal", semana);
        editor.putString("mensual", mes);
        editor.commit();
    }


}
