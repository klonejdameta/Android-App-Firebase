package com.kmeta.logicalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kmeta.logicalapp.Activities.CustomersActivity;
import com.kmeta.logicalapp.Activities.DocumentsActivity;
import com.kmeta.logicalapp.Activities.LoginActivity;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Fragments.CustomersFragment;
import com.kmeta.logicalapp.Fragments.DocumentsFragment;
import com.kmeta.logicalapp.Fragments.HomeFragment;
import com.kmeta.logicalapp.Fragments.MapFragment;
import com.kmeta.logicalapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    BottomNavigationView bottomNavigationView;
    DatabaseConnector databaseConnector;
    ActivityMainBinding binding;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseConnector = new DatabaseConnector(this);

        CustomersFragment customersFragment = new CustomersFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, customersFragment).commit();

        DocumentsFragment documentsFragment = new DocumentsFragment();
        FragmentTransaction fragmentTransactionDocuments = getSupportFragmentManager().beginTransaction();
        fragmentTransactionDocuments.replace(R.id.frame_layout, documentsFragment).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.fab);

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.customers:
                    replaceFragment(new CustomersFragment());
                    break;
                case R.id.documents:
                    replaceFragment(new DocumentsFragment());
                    break;
                case R.id.map:
                    replaceFragment(new MapFragment());
                    break;
            }

            return true;
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });

    }

    public void logout(View view) {
        logoutMenu(MainActivity.this);
    }

    private void logoutMenu(MainActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        LinearLayout customersLayout = dialog.findViewById(R.id.layoutCustomers);
        LinearLayout documentsLayout = dialog.findViewById(R.id.layoutDocuments);

        customersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Create a customer is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CustomersActivity.class);
                startActivity(intent);
            }
        });

        documentsLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Create a document is Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DocumentsActivity.class);
                startActivity(intent);

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}