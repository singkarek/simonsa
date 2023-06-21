package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddTransaction2 extends AppCompatActivity {
    LinearLayout listproduct;
    ImageButton back;
    Cursor cursor;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction2);
        getSupportActionBar().hide();

        back = findViewById(R.id.btn_back);
        next = findViewById(R.id.btn_next);
        listproduct = findViewById(R.id.layout_list_product);
        refreshList();

        /*  Dummy
        String[] prod = getResources().getStringArray(R.array.list);
        perulangan untuk add product, sementara
        for(int i = 0; i < prod.length; i++){
            //View add = getLayoutInflater().inflate(R.layout.product, listproduct, false);
            //TextView text = add.findViewById(R.id.product_name);
            //text.setText(prod[i]);
            //listproduct.addView(add);
        }*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(AddTransaction2.this, AddTransaction3.class);
                startActivity(next);
                finish();
            }
        });
    }

    public void refreshList() {
        SQLiteDB d = new SQLiteDB(AddTransaction2.this);
        SQLiteDatabase db = d.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM product", null);
        if (cursor.getCount() > 0) {
            for (int r = 0; r < cursor.getCount(); r++) {
                cursor.moveToPosition(r);
                View add = getLayoutInflater().inflate(R.layout.product, listproduct, false);
                ImageButton plus = add.findViewById(R.id.plus);
                EditText amount = add.findViewById(R.id.amount);
                ImageButton minus = add.findViewById(R.id.minus);
                int initialint = 0;
                amount.setText(String.valueOf(initialint));
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        amount.setText(String.valueOf(Integer.parseInt(amount.getText().toString()) + 1));
                    }
                });
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        amount.setText(String.valueOf(Integer.parseInt(amount.getText().toString()) - 1));
                    }
                });
                TextView text = add.findViewById(R.id.product_name);
                text.setText(cursor.getString(1));
                listproduct.addView(add);
            }
        }
    }
}