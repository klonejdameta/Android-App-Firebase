package com.kmeta.logicalapp.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmeta.logicalapp.Activities.ViewUsersActivity;
import com.kmeta.logicalapp.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcomeTextView = rootView.findViewById(R.id.welcome_text);
        SharedPreferences preferences = requireActivity().getSharedPreferences("my_prefs", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        welcomeTextView.setText("Welcome, " + username);

        rootView.findViewById(R.id.button_view_users).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ViewUsersActivity.class);
            startActivity(intent);
        });

        return rootView;
    }
}