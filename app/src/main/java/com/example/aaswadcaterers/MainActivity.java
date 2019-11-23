package com.example.aaswadcaterers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(getApplicationContext(),showActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }


    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void showdata(View view) {
        startActivity(new Intent(getApplicationContext(),Retrieve.class));
    }
}