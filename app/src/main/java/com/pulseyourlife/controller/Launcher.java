package com.pulseyourlife.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pulseyourlife.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class Launcher extends AppCompatActivity {

    private String value;
    private  Context cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Intent toBeLaunched;
        String usersFile = "users_file.txt";
        String passwordsFile = "passwords_file.txt";
        String currUser = "current_user.txt";
        String cred = "david";
        String un = "unlogin";
        cont = this;
        try {
            FileOutputStream fos = openFileOutput(usersFile, Context.MODE_PRIVATE);
            fos.write(cred.getBytes());
            fos.close();
            FileOutputStream fos2 = openFileOutput(passwordsFile, Context.MODE_PRIVATE);
            fos2.write(cred.getBytes());
            fos2.close();
            // fos3 = openFileOutput(currUser, Context.MODE_PRIVATE);
            //fos3.write(un.getBytes());
            //fos3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            SharedPreferences shad = getSharedPreferences("user", cont.MODE_PRIVATE);
            value= shad.getString("User", "unlogin");
        }catch(Exception e ){
            e.printStackTrace();
        }
        if (!value.equals("unlogin")) {
            toBeLaunched = new Intent(this,Home.class);
        }else{
            toBeLaunched = new Intent(this, Main.class);
        }

        startActivity(toBeLaunched);
        finish();

    }
}
