package com.digiponic.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    CheckBox keeplog;
    Cursor cursor;
    Button login;
    SharedPreferences shrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        user = findViewById(R.id.txt_username);
        pass = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);
        keeplog = findViewById(R.id.cb_remember);
        shrd = getSharedPreferences("LogInfo", Context.MODE_PRIVATE);
        String loginstatus = shrd.getString(getResources().getString(R.string.prefstatus), "");
        if (loginstatus.equals("loggedin")) {
            startActivity(new Intent(MainActivity.this, UserActivity.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = user.getText().toString();
                String p = pass.getText().toString();
                try {
                    if (u.length() > 0 && p.length() > 0) {
                        SQLiteDB db = new SQLiteDB(MainActivity.this);
                        SQLiteDatabase a = db.getReadableDatabase();
                        cursor = a.rawQuery("SELECT * FROM users WHERE username = '" + u + "'AND password = '" + p + "'", null);
                        if (cursor.getCount() > 0) {
                            cursor.moveToPosition(0);
                            String id = cursor.getString(0).toString();
                            String name = cursor.getString(3).toString();
                            SharedPreferences.Editor editor = shrd.edit();
                            if (keeplog.isChecked()) {
                                editor.putString(getResources().getString(R.string.prefstatus), "loggedin");
                            } else {
                                editor.putString(getResources().getString(R.string.prefstatus), "loggedout");
                            }
                            editor.apply();
                            Intent login = new Intent(MainActivity.this, Presence.class);
                            login.putExtra("id", id);
                            login.putExtra("name", name);
                            startActivity(login);
                            finish();
                            db.close();
                        } else {
                            Toast.makeText(MainActivity.this, "Email or Password is wrong", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "All field required", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}