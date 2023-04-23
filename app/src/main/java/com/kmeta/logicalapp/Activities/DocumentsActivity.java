package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.MainActivity;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.databinding.ActivityDocumentsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DocumentsActivity extends AppCompatActivity {
    CollectionReference documentsRef;
    ActivityDocumentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        documentsRef = FirebaseFirestore.getInstance().collection("documents");
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

                String documentId = documentsRef.document().getId();
                DocumentsModel document = new DocumentsModel(documentId, document_number, document_date, amount, customer);
                documentsRef.document(documentId).set(document);
                Toast.makeText(DocumentsActivity.this, "Document Created Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }

    public void documentsActivityBackButton(View view) {
        onBackPressed();
    }
}