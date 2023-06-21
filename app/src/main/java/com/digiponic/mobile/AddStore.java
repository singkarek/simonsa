package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddStore extends AppCompatActivity {
    Button save;
    ImageButton back;
    EditText storename, sellername, address, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);
        getSupportActionBar().hide();

        save = findViewById(R.id.btn_save);
        back = findViewById(R.id.btn_back);
        storename = findViewById(R.id.txt_storename);
        sellername = findViewById(R.id.txt_sellername);
        address = findViewById(R.id.txt_address);
        phone = findViewById(R.id.txt_phone);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (storename.length() > 0 && sellername.length() > 0 && address.length() > 0 && phone.length() > 0) {
                        String store = storename.getText().toString();
                        String seller = sellername.getText().toString();
                        String addr = address.getText().toString();
                        Dialog dialog = new Dialog(AddStore.this);
                        dialog.setContentView(R.layout.added_dialog);
                        Button ok = dialog.findViewById(R.id.btn_ok);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                        SQLiteDB db = new SQLiteDB(AddStore.this);
                        SQLiteDatabase dbs = db.getWritableDatabase();
                        dbs.execSQL("INSERT INTO store (store_id, seller_name, store_name, address, phone) VALUES (null, '" + seller + "', '" + store + "', '" + addr + "', '" + phone.getText() + "')");
                        db.close();
                        dialog.show();
                    } else {
                        Toast.makeText(AddStore.this, "All field required", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(AddStore.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}