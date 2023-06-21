package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Rankings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        getSupportActionBar().hide();
    }
}