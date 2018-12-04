package com.pulseyourlife.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pulseyourlife.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private ArrayList<String> users  = new ArrayList<>();
    private ArrayList<String> passwords  = new ArrayList<>();
    private String currentUser;
    private String currentPwd;
    private EditText editPassword;
    private EditText editEmail;
    private Context cont;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont = this;
        shared =  getSharedPreferences("User", cont.MODE_PRIVATE);
        setButtonLogIn();
        setRegisterText();
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
                if((indexPassword != -1 && indexUser != -1) && indexPassword == indexUser ) {
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("User", currentUser);
                    editor.apply();
                    Intent home = new Intent(Main.this, Home.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(home);
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(Main.this).create();
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

    private void setRegisterText(){
        TextView register_textView = (TextView) findViewById(R.id.textView_Register);

        String register_text = getString(R.string.main_donthaveacc) +" "+ getString(R.string.register_name);

        SpannableString ss = new SpannableString(register_text);

        setClickableSpan(register_text, ss, getString(R.string.register_name),Register.class);

        register_textView.setText(ss);
        register_textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setClickableSpan(String text, final SpannableString ss, String stringToSearch, final Class classIntentDestiny){
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(Main.this, classIntentDestiny);
                startActivity(i);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorAccent));
            }
        };
        ss.setSpan(clickableSpan, text.indexOf(stringToSearch),text.indexOf(stringToSearch)+stringToSearch.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
