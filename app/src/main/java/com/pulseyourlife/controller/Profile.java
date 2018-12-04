package com.pulseyourlife.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pulseyourlife.R;

public class Profile extends Fragment {

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
                name.setFocusable(true);
                name.setClickable(true);
                surname.setFocusable(true);
                surname.setClickable(true);
                height.setFocusable(true);
                height.setClickable(true);
                weight.setFocusable(true);
                weight.setClickable(true);
                buttonSave.setBackgroundResource(R.drawable.button_rounded_corner);
                buttonUpdate.setBackgroundResource(R.drawable.button_disabled_rounded_corner);
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name.setFocusable(false);
                name.setClickable(false);
                surname.setFocusable(false);
                surname.setClickable(false);
                height.setFocusable(false);
                height.setClickable(false);
                weight.setFocusable(false);
                weight.setClickable(false);
                buttonUpdate.setBackgroundResource(R.drawable.button_rounded_corner);
                buttonSave.setBackgroundResource(R.drawable.button_disabled_rounded_corner);
            }
        });
    }
}
