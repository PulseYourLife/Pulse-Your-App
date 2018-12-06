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
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pulseyourlife.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.firebase.auth.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import Logic.User;

public class Main extends AppCompatActivity {

    private ArrayList<String> users  = new ArrayList<>();
    private ArrayList<String> passwords  = new ArrayList<>();
    private String currentUser;
    private String currentPwd;
    private EditText editPassword;
    private EditText editEmail;
    private Context cont;
    private SharedPreferences shared;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userReferenceData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont = this;
        firebaseAuth = FirebaseAuth.getInstance();
        shared =  getSharedPreferences("user", cont.MODE_PRIVATE);
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
                logUser();

                /*currentPwd = editPassword.getText().toString();
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
                }*/
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

    public void logUser(){
        currentPwd = editPassword.getText().toString();
        currentUser = editEmail.getText().toString();
        if (TextUtils.isEmpty(currentUser)) {
            Toast.makeText(this,"Email Field is Empty",Toast.LENGTH_LONG).show();

            return;
        }
        else if (TextUtils.isEmpty(currentPwd)) {
            Toast.makeText(this, "Password Field is Empty\"", Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(currentUser, currentPwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            String[] welcome = currentUser.getDisplayName().split(";");
                            Toast.makeText(Main.this, "Welcome: " + welcome[0], Toast.LENGTH_LONG).show();

                            Intent goHome = new Intent(getApplication(), Home.class);
                            //Bundle userSend = new Bundle();
                            //userSend.putSerializable("userData",userSend);
                            startActivity(goHome);
                            /*userReferenceData = FirebaseDatabase.getInstance().getReference();
                            final FirebaseUser userDataLoad = currentUser;
                            userReferenceData.child("Users").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                        if(snapshot.getKey().equals(userDataLoad.getEmail())){
                                            User loadedUser = dataSnapshot.getValue(User.class);
                                            Log.e("User DATTTTTTTTTTTA", loadedUser.getName());
                                            Log.e("User DATTTTTTTTTTTA", loadedUser.getAddress());

                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });*/



                        } else {

                            Toast.makeText(Main.this, "Your Email Or Password Are Incorrect", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }


}
