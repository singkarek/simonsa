package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailStore extends AppCompatActivity {
    LinearLayout historylist;
    Cursor cursor;
    ImageButton back;
    Button transaction;
    TextView tvhead, tvname, tvaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        getSupportActionBar().hide();

        historylist = findViewById(R.id.transaction_history_container);
        tvhead = findViewById(R.id.tv_head_detailstore);
        tvname = findViewById(R.id.store_name);
        back = findViewById(R.id.btn_back);
        transaction = findViewById(R.id.btn_transaction);
        tvaddress = findViewById(R.id.store_address);

        String idpassed = getIntent().getStringExtra("storeid");
        SQLiteDB d = new SQLiteDB(DetailStore.this);
        SQLiteDatabase db = d.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM store WHERE store_id = '" + idpassed + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tvhead.setText(cursor.getString(2));
            tvname.setText(cursor.getString(2));
            tvaddress.setText(cursor.getString(3));
        }
        for (int i = 0; i < 5; i++) {
            View add = getLayoutInflater().inflate(R.layout.transaction_history, historylist, false);
            historylist.addView(add);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailStore.this, AddTransaction2.class);
                intent.putExtra("storeid", idpassed);
                startActivity(intent);
                finish();
            }
        });
    }
}