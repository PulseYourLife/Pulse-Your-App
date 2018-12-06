package com.pulseyourlife.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pulseyourlife.R;

import Logic.User;

public class Register extends AppCompatActivity {

    private EditText emailT, psswdT, cpsswdT, nameT;
    private String email, psswd, cpsswd, name;
    private final String usersFile = "users_file.txt";
    private final String passwordsFile = "passwords_file.txt";
    private final String namesFile = "names_file.txt";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailT  = (EditText) findViewById(R.id.editText_email);
        psswdT  = (EditText) findViewById(R.id.editText_password);
        cpsswdT  = (EditText) findViewById(R.id.editText_confirm_password);
        nameT  = (EditText) findViewById(R.id.editText_name);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Pulse_Your_Life_User_Data");

        setToolbarBackButton();
        setButtonSignUp();
    }

    private void setButtonSignUp() {
        Button btn_signUp = (Button) findViewById(R.id.button_signup);

        btn_signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                postUser();


                /*
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
                }*/

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

    public void postUser(){
        email = emailT.getText().toString().trim();
        psswd = psswdT.getText().toString();
        cpsswd = cpsswdT.getText().toString();
        name = nameT.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email Field is Empty",Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(psswd)){
            Toast.makeText(this,"Password Field is Empty",Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Name Field is Empty",Toast.LENGTH_LONG).show();
            return;
        }
        else if(!psswd.equals(cpsswd)){
            Toast.makeText(this,"Passwords Doesn't Match",Toast.LENGTH_LONG).show();
            return;
        }
        else{

            firebaseAuth.createUserWithEmailAndPassword(email, psswd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            User user = new User(name,email,psswd,"0","0");
                            if(task.isSuccessful()){

                                databaseReference.child("Users").push().setValue(user);
                                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name+";0;0")
                                        .setPhotoUri(Uri.parse("https://cdn4.iconfinder.com/data/icons/ios-edge-glyph-12/25/User-Circle-512.png"))
                                        .build();
                                currentUser.updateProfile(profileUpdates);
                                Toast.makeText(Register.this,"Successful "+ currentUser.getDisplayName(),Toast.LENGTH_LONG).show();
                                Intent goMain = new Intent(getApplication(), Home.class);
                                startActivity(goMain);
                            }else{
                                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(Register.this,"User Already Exist ",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(Register.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                                }


                            }
                        }
                    });

        }


    }
}