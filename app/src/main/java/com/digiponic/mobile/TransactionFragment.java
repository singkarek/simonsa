package com.digiponic.mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionFragment} factory method to
 * create an instance of this fragment.
 */
public class TransactionFragment extends Fragment {
    LinearLayout transaction_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        transaction_list = view.findViewById(R.id.transaction_layout);
        for (int k = 0; k < 10; k++) {
            View add = getLayoutInflater().inflate(R.layout.transaction, transaction_list, false);
            transaction_list.addView(add);
        }
        int i = transaction_list.getChildCount();
        //Perulangan untuk layout klik, sementara
        for (int r = 0; r < i; r++) {
            transaction_list.getChildAt(r).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(getActivity(), DetailTransaction.class);
                    startActivity(detail);
                }
            });
        }
    }
}