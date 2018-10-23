package com.pulseyourlife.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pulseyourlife.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Intent toBeLaunched;
        String FILENAME = "users_file.txt";
        String cred = "david";
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(cred.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
