package com.digiponic.mobile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoresFragment} factory method to
 * create an instance of this fragment.
 */
public class StoresFragment extends Fragment {
    ImageButton addstore;
    LinearLayout storelist;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_stores, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addstore = view.findViewById(R.id.btn_addstore);
        storelist = view.findViewById(R.id.layout_storelist);
        refreshList();
        /*for(int k = 0; k < storelist.getChildCount(); k++){
            storelist.getChildAt(k).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), DetailStore.class);
                    intent.putExtra("storeid", )
                }
            });
        }*/
        addstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getActivity(), AddStore.class);
                startActivity(add);
            }
        });
    }

    public void refreshList() {
        SQLiteDB d = new SQLiteDB(getActivity());
        SQLiteDatabase db = d.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM store", null);
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                View add = getLayoutInflater().inflate(R.layout.store, storelist, false);
                TextView storename = add.findViewById(R.id.store_name);
                TextView address = add.findViewById(R.id.store_address);
                storename.setText(cursor.getString(2).toString());
                address.setText(cursor.getString(3).toString());
                String storeid = cursor.getString(0);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DetailStore.class);
                        intent.putExtra("storeid", storeid);
                        startActivity(intent);
                    }
                });
                storelist.addView(add);
            }
        }
    }
}