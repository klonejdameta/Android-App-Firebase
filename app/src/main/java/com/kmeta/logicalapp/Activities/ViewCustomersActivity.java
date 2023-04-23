package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kmeta.logicalapp.Adapters.CustomerAdapter;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.R;

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

        DatabaseConnector databaseConnector = new DatabaseConnector(this);
/*        List<CustomerModel> customerModels = databaseConnector.getCustomerList();

        if (customerModels.size() > 0){
            CustomerAdapter customerAdapter = new CustomerAdapter(customerModels, ViewCustomersActivity.this);
            recyclerView.setAdapter(customerAdapter);
        }else {
            Toast.makeText(this, "There is no customer in the database", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void customersBackButton(View view) {
        onBackPressed();
    }
}