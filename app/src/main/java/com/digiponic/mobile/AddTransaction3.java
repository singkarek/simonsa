package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddTransaction3 extends AppCompatActivity {
    ImageButton back;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction3);
        getSupportActionBar().hide();

        back = findViewById(R.id.btn_back);
        save = findViewById(R.id.btn_save);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AddTransaction3.this);
                dialog.setContentView(R.layout.added_dialog);
                Button ok = dialog.findViewById(R.id.btn_ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                dialog.show();
            }
        });
    }
}