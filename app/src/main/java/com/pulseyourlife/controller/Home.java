package com.pulseyourlife.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pulseyourlife.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SharedPreferences sharedPreferences;
    private TextView nameNav, emailNav;
    private ImageView imageNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nameNav = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nameNav);
        emailNav = (TextView) navigationView.getHeaderView(0).findViewById(R.id.emailNav);
        imageNav = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.picNav);
        sharedPreferences = getSharedPreferences("User", this.MODE_PRIVATE);
        String word = sharedPreferences.getString("User", "");
        emailNav.setText(word);
        try {
            nameNav.setText("nombre: "+getName(word));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Statistics()).commit();
            navigationView.setCheckedItem(R.id.nav_statistic);
        }


    }
    public String getName(String email) throws IOException {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        String name = "";
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("names_file.txt")));
            for (String line = fin.readLine(); line != null; line = fin.readLine()) {
                names.add(line);
            }
            fin.close();
            BufferedReader fin2 = new BufferedReader(new InputStreamReader(openFileInput("users_file.txt")));
            for (String line = fin.readLine(); line != null; line = fin.readLine()) {
                emails.add(line);
            }
            fin2.close();
            int index = emails.indexOf(email);
            name= names.get(index);

        }catch(Exception e){
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_statistic:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Statistics()).commit();
                getSupportActionBar().setTitle(R.string.statistics_name);
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new About()).commit();
                getSupportActionBar().setTitle(R.string.about_name);
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).commit();
                getSupportActionBar().setTitle(R.string.profile_name);
                break;
            case R.id.nav_logout:
                Toast.makeText(this,  getResources().getString(R.string.logout), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("User");
                editor.apply();
                Intent main = new Intent(Home.this, Main.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
