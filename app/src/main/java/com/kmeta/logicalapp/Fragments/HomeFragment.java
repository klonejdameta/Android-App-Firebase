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
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.R;
import com.kmeta.logicalapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    DatabaseConnector databaseConnector;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        databaseConnector = new DatabaseConnector(getContext());
        TextView welcomeTextView = rootView.findViewById(R.id.welcome_text);
        SharedPreferences preferences = getActivity().getSharedPreferences("my_prefs", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        welcomeTextView.setText("Welcome, " + username);

        rootView.findViewById(R.id.button_view_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewUsersActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}