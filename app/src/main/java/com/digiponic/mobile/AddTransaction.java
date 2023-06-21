package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class AddTransaction extends AppCompatActivity {
    ImageButton back;
    RelativeLayout store_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        getSupportActionBar().hide();

        back = findViewById(R.id.btn_back);
        store_list = findViewById(R.id.layout_store_list);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int i = store_list.getChildCount();
        //perulangan untuk store onclick, sementara
        for (int r = 0; r < i; r++) {
            store_list.getChildAt(r).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent add = new Intent(AddTransaction.this, AddTransaction2.class);
                    startActivity(add);
                    finish();
                }
            });
        }
    }
}