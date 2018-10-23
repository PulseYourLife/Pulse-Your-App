package com.pulseyourlife.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pulseyourlife.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Statistics extends AppCompatActivity {
    private String text;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        try {

        }catch(Exception e){
            e.printStackTrace();
        }


        tv= (TextView) findViewById(R.id.textView);
        tv.setText(text);
    }


}
