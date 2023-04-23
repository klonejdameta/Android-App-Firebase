package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.MainActivity;
import com.kmeta.logicalapp.databinding.ActivityDocumentsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DocumentsActivity extends AppCompatActivity {
    DatabaseConnector databaseConnector;
    ActivityDocumentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseConnector = new DatabaseConnector(this);
        binding.documentCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String document_number = binding.documentNo.getText().toString();
                if (document_number.equals("")) {
                    Toast.makeText(DocumentsActivity.this, "Please provide a document number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isDigitsOnly(document_number)) {
                    Toast.makeText(DocumentsActivity.this, "Please enter a valid document number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String document_date = binding.documentDate.getText().toString();
                if (document_date.equals("")) {
                    Toast.makeText(DocumentsActivity.this, "Please provide a document date", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                sdf.setLenient(false);

                try {
                    Date date = sdf.parse(document_date);
                    // Date is valid
                } catch (ParseException e) {
                    // Date is invalid
                    Toast.makeText(DocumentsActivity.this, "Please enter a valid document date (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                    return;
                }

                String amount = binding.documentAmount.getText().toString();
                if (amount.equals("")) {
                    Toast.makeText(DocumentsActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(amount)) {
                    Toast.makeText(DocumentsActivity.this, "Please enter a valid amount number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String customer = binding.documentCustomer.getText().toString();
                if (customer.equals("")) {
                    Toast.makeText(DocumentsActivity.this, "Please enter customer", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (document_number.equals("") || document_date.equals("") || amount.equals("") || customer.equals("")) {
                    Toast.makeText(DocumentsActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
/*                    Boolean insert = databaseConnector.insertDataDocuments(document_number, document_date, amount, customer);
                    if (insert == true) {
                        Toast.makeText(DocumentsActivity.this, "Document Created Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DocumentsActivity.this, "Document Creation Failed", Toast.LENGTH_SHORT).show();
                    }*/
                }

            }
        });

    }

    public void documentsActivityBackButton(View view) {
        onBackPressed();
    }
}