package com.pulseyourlife.Controller;

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
import android.widget.TextView;
import android.widget.Toast;

import com.pulseyourlife.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setLoginText();
    }

    private void setLoginText(){
        TextView login_textView = findViewById(R.id.textView_Login);

        String login_text = getString(R.string.main_haveacc) +" "+ getString(R.string.login_name);

        SpannableString ss = new SpannableString(login_text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent login = new Intent(Main.this, Login.class);
                Toast.makeText(Main.this, "this", Toast.LENGTH_SHORT).show();
                startActivity(login);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorAccent));
            }
        };

        ss.setSpan(clickableSpan, login_text.indexOf(getString(R.string.login_name)),login_text.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login_textView.setText(ss);
        login_textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
