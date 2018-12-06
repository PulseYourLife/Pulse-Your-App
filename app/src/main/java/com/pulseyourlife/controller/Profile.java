package com.pulseyourlife.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pulseyourlife.R;

import java.util.ArrayList;
import java.util.List;

import Logic.User;

public class Profile extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    private DatabaseReference userReferenceData = FirebaseDatabase.getInstance().getReference("Users");
    private FirebaseAuth mAuth =  FirebaseAuth.getInstance();
    private List<User> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Button buttonSave = (Button) getActivity().findViewById(R.id.button_profileSave);
        final Button buttonUpdate = (Button) getActivity().findViewById(R.id.button_profileUpdate);
        final EditText name = (EditText) getActivity().findViewById(R.id.editText_name);
        final EditText email = (EditText) getActivity().findViewById(R.id.editText_email);
        final EditText surname = (EditText) getActivity().findViewById(R.id.editText_surname);
        final EditText height = (EditText) getActivity().findViewById(R.id.editText_height);
        final EditText weight = (EditText) getActivity().findViewById(R.id.editText_weight);
        final Button buttonSavem = (Button) getActivity().findViewById(R.id.button_profileSavem);


        String[] parts1 = mAuth.getCurrentUser().getDisplayName().split(";");
        String[] parts = parts1[0].split(" ");
        name.setText(parts[0]);
        surname.setText(parts[1]);
        users = new ArrayList<>();
        email.setText(mAuth.getCurrentUser().getEmail());
        weight.setText(parts1[1]);
        height.setText(parts1[2]);




        name.setFocusable(false);
        name.setClickable(false);
        email.setFocusable(false);
        email.setClickable(false);
        email.setFocusableInTouchMode(false);
        email.setCursorVisible(false);
        surname.setFocusable(false);
        surname.setClickable(false);
        height.setFocusable(false);
        height.setClickable(false);
        weight.setFocusable(false);
        weight.setClickable(false);
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Fragment newFrag = new Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, new Settings()).commit();
                transaction.addToBackStack(null);

                buttonSave.setBackgroundResource(R.drawable.button_rounded_corner);
                buttonUpdate.setBackgroundResource(R.drawable.button_disabled_rounded_corner);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
