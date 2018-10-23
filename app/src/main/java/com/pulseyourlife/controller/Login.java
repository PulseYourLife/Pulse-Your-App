package com.pulseyourlife.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pulseyourlife.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    private String text  = "";
    private EditText editPassword;
    private EditText editEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbarBackButton();
        setButtonLogIn();
    }

    private void setButtonLogIn() {
        Button btn_login = (Button) findViewById(R.id.button_login);
        editEmail = (EditText) findViewById(R.id.editText_email);
        editPassword = (EditText) findViewById(R.id.editText_password);
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("users_file.txt")));
            text = fin.readLine();
            fin.close();
            btn_login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(editEmail.getText().toString().equals(text) && editPassword.getText().toString().equals(text)) {
                        Intent home = new Intent(Login.this, Statistics.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(home);
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                        alertDialog.setTitle("Authentication error");
                        alertDialog.setMessage("Please verify your credentials");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
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
