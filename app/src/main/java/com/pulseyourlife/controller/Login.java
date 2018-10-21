package com.pulseyourlife.controller;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.pulseyourlife.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbarBackButton();
        setButtonLogIn();
    }

    private void setButtonLogIn() {
        Button btn_login = (Button) findViewById(R.id.button_login);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent home = new Intent(Login.this, Statistics.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(home);
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
