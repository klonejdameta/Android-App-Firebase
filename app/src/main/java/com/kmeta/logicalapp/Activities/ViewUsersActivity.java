package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.kmeta.logicalapp.Adapters.DocumentsAdapter;
import com.kmeta.logicalapp.Adapters.UsersAdapter;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.Models.UsersModel;
import com.kmeta.logicalapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<UsersModel> usersModels = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                UsersModel userModel = document.toObject(UsersModel.class);
                                userModel.setId(document.getId());
                                usersModels.add(userModel);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (usersModels.size() > 0) {
                            UsersAdapter userAdapter = new UsersAdapter(usersModels, ViewUsersActivity.this);
                            recyclerView.setAdapter(userAdapter);
                        } else {
                            Toast.makeText(this, "There is no user in the database", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Error getting users: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void usersBackButton(View view) {
        onBackPressed();
    }
}