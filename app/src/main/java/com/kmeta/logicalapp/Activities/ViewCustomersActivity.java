package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.kmeta.logicalapp.Adapters.CustomerAdapter;
import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("customers")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<CustomerModel> customerModels = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                CustomerModel customerModel = document.toObject(CustomerModel.class);
                                customerModel.setId(document.getId());
                                customerModels.add(customerModel);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (customerModels.size() > 0) {
                            CustomerAdapter customerAdapter = new CustomerAdapter(customerModels, ViewCustomersActivity.this);
                            recyclerView.setAdapter(customerAdapter);
                        } else {
                            Toast.makeText(this, "There is no customer in the database", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Error getting customers: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void customersBackButton(View view) {
        onBackPressed();
    }
}