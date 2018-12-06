package com.pulseyourlife.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pulseyourlife.R;

import java.util.ArrayList;
import java.util.List;

import Logic.User;

public class Settings extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    private DatabaseReference userReferenceData = FirebaseDatabase.getInstance().getReference("Users");
    private FirebaseAuth mAuth =  FirebaseAuth.getInstance();
    private List<User> users;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modifyprofile, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Button buttonUpdatem = (Button) getActivity().findViewById(R.id.button_profileUpdate);
        final EditText namem = (EditText) getActivity().findViewById(R.id.editText_namem);
        final EditText emailm = (EditText) getActivity().findViewById(R.id.editText_emailm);
        final EditText surnamem = (EditText) getActivity().findViewById(R.id.editText_surnamem);
        final EditText heightm = (EditText) getActivity().findViewById(R.id.editText_heightm);
        final EditText weightm = (EditText) getActivity().findViewById(R.id.editText_weightm);
        final Button buttonSavem = (Button) getActivity().findViewById(R.id.button_profileSavem);


        buttonSavem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Bundle userSend = new Bundle();
                //userSend.putSerializable("userData",userSend);
            }
        });

        final String[] parts1 = mAuth.getCurrentUser().getDisplayName().split(";");
        final String[] parts = parts1[0].split(" ");
        namem.setText(parts[0]);
        surnamem.setText(parts[1]);
        weightm.setText(parts1[1]);
        heightm.setText(parts1[2]);
        users = new ArrayList<>();

        emailm.setText(mAuth.getCurrentUser().getEmail());
        buttonSavem.setBackgroundResource(R.drawable.button_rounded_corner);
        //buttonUpdatem.setBackgroundResource(R.drawable.button_disabled_rounded_corner);


        buttonSavem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(!emailm.getText().toString().equals(mAuth.getCurrentUser().getEmail())){
                    user.updateEmail(emailm.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Email Updating", "User email address updated.");
                                    }
                                }

                            });
                }

                if(!weightm.getText().toString().equals(parts1[1]) || !heightm.getText().toString().equals(parts1[2])){
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(namem.getText().toString() +" " + surnamem.getText().toString()+";"
                            +weightm.getText().toString()+";"+heightm.getText().toString())
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Log.d("Edit Name", "User profile updated.");
                                        Toast.makeText(getActivity(),"Your Data Has Been Updated ",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }

                if(!surnamem.getText().toString().equals(parts[1])|| !namem.getText().toString().equals(parts[0]) ){
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(namem.getText().toString() +" " + surnamem.getText().toString())
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Edit Name", "User profile updated.");
                                        Toast.makeText(getActivity(),"Your Data Has Been Updated ",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }

                Fragment newFrag = new Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, new Statistics()).commit();
                transaction.addToBackStack(null);

                buttonSavem.setBackgroundResource(R.drawable.button_rounded_corner);
                //buttonUpdatem.setBackgroundResource(R.drawable.button_disabled_rounded_corner);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
