package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kmeta.logicalapp.Adapters.UsersAdapter;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Models.UsersModel;
import com.kmeta.logicalapp.R;

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

        DatabaseConnector databaseConnector = new DatabaseConnector(this);
/*        List<UsersModel> usersModels = databaseConnector.getUsersList();

        if (usersModels.size() > 0) {
            UsersAdapter usersAdapters = new UsersAdapter(usersModels, ViewUsersActivity.this);
            recyclerView.setAdapter(usersAdapters);
        } else {
            Toast.makeText(this, "There are no users in the database", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void usersBackButton(View view) {
        onBackPressed();
    }
}