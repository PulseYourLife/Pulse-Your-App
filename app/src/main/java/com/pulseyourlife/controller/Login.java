package com.pulseyourlife.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pulseyourlife.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private ArrayList<String> users  = new ArrayList<>();
    private ArrayList<String> passwords  = new ArrayList<>();
    private String currentUser;
    private String currentPwd;
    private EditText editPassword;
    private EditText editEmail;
    private Context cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cont = this;
        SharedPreferences shared =  getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        setToolbarBackButton();
        setButtonLogIn();
    }

    private void setButtonLogIn() {
        Button btn_login = (Button) findViewById(R.id.button_login);
        editEmail = (EditText) findViewById(R.id.editText_email);
        editPassword = (EditText) findViewById(R.id.editText_password);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                currentPwd = editPassword.getText().toString();
                currentUser = editEmail.getText().toString();
                try {
                    BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("users_file.txt")));
                    for(String line = fin.readLine(); line != null; line = fin.readLine()){
                        users.add(line);
                    }
                    fin.close();
                    BufferedReader fin2 = new BufferedReader(new InputStreamReader(openFileInput("passwords_file.txt")));
                    for(String line = fin2.readLine(); line != null; line = fin2.readLine()){
                        passwords.add(line);
                    }
                    fin2.close();

                }catch(Exception e){
                    e.printStackTrace();
                }
                int indexUser = users.indexOf(currentUser);
                int indexPassword = users.indexOf(currentPwd);
                if((indexPassword != -1 && indexPassword != -1) && indexPassword == indexUser ) {
                    SharedPreferences shad = getPreferences(cont.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shad.edit();
                    editor.putString("User", currentUser);
                    editor.commit();

                    SharedPreferences shado = getPreferences(cont.MODE_PRIVATE);
                    String value= shado.getString("User", "unlogin");
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    value, Toast.LENGTH_SHORT);

                    toast1.show();
                    Intent home = new Intent(Login.this, Statistics.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(home);
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                    alertDialog.setTitle(getString(R.string.auth_error));
                    alertDialog.setMessage(getString(R.string.login_error));
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