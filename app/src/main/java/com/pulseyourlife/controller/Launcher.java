package com.pulseyourlife.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pulseyourlife.R;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Intent toBeLaunched;
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        FirebaseUser user = firebaseAuth.getCurrentUser();
        String user = null;
        if (user != null) {
            toBeLaunched = new Intent(this,Statistics.class);
        }else{
            toBeLaunched = new Intent(this, Main.class);
        }

        startActivity(toBeLaunched);
        finish();

    }
}
