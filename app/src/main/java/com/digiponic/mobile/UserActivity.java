package com.digiponic.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserActivity extends AppCompatActivity {
    BottomNavigationView navbar;
    NavController navcont;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();

        navbar = findViewById(R.id.navbar);
        fab = findViewById(R.id.fab);
        navcont = Navigation.findNavController(this, R.id.nav_fragment);
        navbar.getMenu().getItem(2).setEnabled(false);
        NavigationUI.setupWithNavController(navbar, navcont);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, AddTransaction.class);
                startActivity(intent);
            }
        });
    }
}