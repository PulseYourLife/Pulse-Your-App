package com.pulseyourlife.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import com.pulseyourlife.R;

public class Statistics extends Fragment {

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        try {

        }catch(Exception e){
            e.printStackTrace();
        }
        tv= (TextView) findViewById(R.id.textView);
        tv.setText(text);
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //TextView textView = (TextView) getView().findViewById(R.id.textView);
        //textView.setText(getString(R.string.login_name));
    }
}
