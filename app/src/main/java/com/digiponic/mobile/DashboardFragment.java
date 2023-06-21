package com.digiponic.mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    TextView profilename, seemore;
    NavController navcon;
    BottomNavigationView navbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profilename = view.findViewById(R.id.profile_name);
        navbar = view.findViewById(R.id.navbar);
        seemore = view.findViewById(R.id.see_more);
        navcon = Navigation.findNavController(view);
        String namepassed = getActivity().getIntent().getStringExtra("name");
        String a = profilename.getText().toString();

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navcon.navigate(R.id.storesFragment);
            }
        });
        profilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Your name is " + a, Toast.LENGTH_LONG).show();
            }
        });
    }
}