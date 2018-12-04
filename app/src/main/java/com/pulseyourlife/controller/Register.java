package com.pulseyourlife.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pulseyourlife.R;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText emailT, psswdT, cpsswdT, nameT;
    private String email, psswd, cpsswd, name;
    private final String usersFile = "users_file.txt";
    private final String passwordsFile = "passwords_file.txt";
    private final String namesFile = "names_file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailT  = (EditText) findViewById(R.id.editText_email);
        psswdT  = (EditText) findViewById(R.id.editText_password);
        cpsswdT  = (EditText) findViewById(R.id.editText_confirm_password);
        nameT  = (EditText) findViewById(R.id.editText_name);
        setToolbarBackButton();
        setButtonSignUp();
    }

    private void setButtonSignUp() {
        Button btn_signUp = (Button) findViewById(R.id.button_signup);

        btn_signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email = emailT.getText().toString();
                psswd = psswdT.getText().toString();
                cpsswd = cpsswdT.getText().toString();
                name = nameT.getText().toString();
                ArrayList<String> users = new ArrayList<>();
                SharedPreferences preferences = getSharedPreferences("datosUsuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String datos = name + "," + psswd + "," + email;
                editor.putString("datos", datos);
                editor.commit();

                try {
                    BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("users_file.txt")));
                    for (String line = fin.readLine(); line != null; line = fin.readLine()) {
                        users.add(line);
                    }
                    fin.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!users.contains(email) && psswd.equals(cpsswd)) {
                    try {
                        FileOutputStream fos = openFileOutput(usersFile, Context.MODE_PRIVATE);
                        fos.write(email.getBytes());
                        fos.close();
                        FileOutputStream fos2 = openFileOutput(passwordsFile, Context.MODE_PRIVATE);
                        fos2.write(psswd.getBytes());
                        fos2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), R.string.register_error, Toast.LENGTH_SHORT);
                    toast1.show();
                }

            }
        });
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
