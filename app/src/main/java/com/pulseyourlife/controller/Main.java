package com.pulseyourlife.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pulseyourlife.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonSignUp();
        setLoginText();
    }

    private void setButtonSignUp() {
        Button btn_signUp = (Button) findViewById(R.id.button_signup);

        btn_signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent signUp = new Intent(Main.this, Register.class);
                startActivity(signUp);
            }
        });
    }

    private void setLoginText(){
        TextView login_textView = (TextView) findViewById(R.id.textView_Login);

        String login_text = getString(R.string.main_haveacc) +" "+ getString(R.string.login_name);

        SpannableString ss = new SpannableString(login_text);

        setClickableLoginSpan(login_text, ss);

        login_textView.setText(ss);
        login_textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setClickableLoginSpan(String login_text, SpannableString ss){
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent login = new Intent(Main.this, Login.class);
                startActivity(login);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorAccent));
            }
        };

        ss.setSpan(clickableSpan, login_text.indexOf(getString(R.string.login_name)),login_text.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
