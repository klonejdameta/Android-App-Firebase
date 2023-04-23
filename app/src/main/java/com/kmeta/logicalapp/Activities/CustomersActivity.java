package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.MainActivity;
import com.kmeta.logicalapp.databinding.ActivityCustomersBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomersActivity extends AppCompatActivity {

    DatabaseConnector databaseConnector;
    ActivityCustomersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseConnector = new DatabaseConnector(this);
        binding.customerCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerFirstName = binding.customerFirstName.getText().toString();
                if (TextUtils.isEmpty(customerFirstName)) {
                    Toast.makeText(CustomersActivity.this, "Please provide the first name", Toast.LENGTH_SHORT).show();
                    return;
                }

                String customerLastName = binding.customerLastName.getText().toString();
                if (TextUtils.isEmpty(customerLastName)) {
                    Toast.makeText(CustomersActivity.this, "Please provide the last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                String customerBirthDate = binding.customerBirthDate.getText().toString();
                if (TextUtils.isEmpty(customerBirthDate)) {
                    Toast.makeText(CustomersActivity.this, "Please enter your birthdate", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                sdf.setLenient(false);

                try {
                    Date date = sdf.parse(customerBirthDate);
                    // Date is valid
                } catch (ParseException e) {
                    // Date is invalid
                    Toast.makeText(CustomersActivity.this, "Please enter a valid birth date (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                    return;
                }

                String customerAddress = binding.customerAddress.getText().toString();
                if (TextUtils.isEmpty(customerAddress)) {
                    Toast.makeText(CustomersActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }

                String customerLongitude = binding.customerLongitude.getText().toString();
                if (TextUtils.isEmpty(customerLongitude)) {
                    Toast.makeText(CustomersActivity.this, "Longitude is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!customerLongitude.matches("-?\\d+(\\.\\d+)?")) {
                    Toast.makeText(CustomersActivity.this, "Invalid longitude value", Toast.LENGTH_SHORT).show();
                    return;
                }
                double longitude = Double.parseDouble(customerLongitude);
                if (longitude < -90 || longitude > 90) {
                    Toast.makeText(CustomersActivity.this, "Invalid longitude value", Toast.LENGTH_SHORT).show();
                    return;
                }

                String customerLatitude = binding.customerLatitude.getText().toString();
                binding.customerLatitude.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                if (TextUtils.isEmpty(customerLatitude)) {
                    Toast.makeText(CustomersActivity.this, "Latitude is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!customerLatitude.matches("-?\\d+(\\.\\d+)?")) {
                    Toast.makeText(CustomersActivity.this, "Invalid latitude value", Toast.LENGTH_SHORT).show();
                    return;
                }
                double latitude = Double.parseDouble(customerLatitude);
                if (latitude < -90 || latitude > 90) {
                    Toast.makeText(CustomersActivity.this, "Invalid latitude value", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(customerFirstName) ||
                        TextUtils.isEmpty(customerLastName) ||
                        TextUtils.isEmpty(customerBirthDate) ||
                        TextUtils.isEmpty(customerAddress) ||
                        TextUtils.isEmpty(customerLongitude) ||
                        TextUtils.isEmpty(customerLatitude)) {
                    Toast.makeText(CustomersActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
/*                    Boolean insert = databaseConnector.insertDataCustomers(customerFirstName, customerLastName, customerBirthDate, customerAddress, customerLongitude, customerLatitude);
                    if (insert == true) {
                        Toast.makeText(CustomersActivity.this, "Customer Created Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CustomersActivity.this, "Customer Creation Failed", Toast.LENGTH_SHORT).show();
                    }*/
                }
            }
        });
    }

    public void customersActivityBackButton(View view) {
        onBackPressed();
    }
}