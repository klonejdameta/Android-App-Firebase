package com.kmeta.logicalapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kmeta.logicalapp.MainActivity;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.Activities.ViewDocumentsActivity;
import com.kmeta.logicalapp.databinding.FragmentDocumentsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DocumentsFragment extends Fragment {
    FragmentDocumentsBinding binding;
    CollectionReference documentsRef;

    public DocumentsFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDocumentsBinding.inflate(inflater, container, false);
        documentsRef = FirebaseFirestore.getInstance().collection("documents");
        binding.buttonAdd.setOnClickListener(v -> {
            String documentNumber = binding.documentNumber.getText().toString();
            if (documentNumber.equals("")) {
                Toast.makeText(getActivity(), "Please provide a document number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!TextUtils.isDigitsOnly(documentNumber)) {
                Toast.makeText(getActivity(), "Please enter a valid document number", Toast.LENGTH_SHORT).show();
                return;
            }

            String documentDate = binding.documentDate.getText().toString();
            if (documentDate.equals("")) {
                Toast.makeText(getActivity(), "Please provide a document date", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            sdf.setLenient(false);

            try {
                Date date = sdf.parse(documentDate);
                Log.d("DATE_VALIDATION", "The date is valid: " + date);
            } catch (ParseException e) {

                Toast.makeText(getActivity(), "Please enter a valid document date (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                return;
            }

            String amount = binding.documentAmount.getText().toString();
            if (amount.equals("")) {
                Toast.makeText(getActivity(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.isDigitsOnly(amount)) {
                Toast.makeText(getActivity(), "Please enter a valid amount number", Toast.LENGTH_SHORT).show();
                return;
            }

            String customer = binding.documentCustomer.getText().toString();
            if (customer.equals("")) {
                Toast.makeText(getActivity(), "Please enter customer", Toast.LENGTH_SHORT).show();
                return;
            }

            String documentId = documentsRef.document().getId();
            DocumentsModel document = new DocumentsModel(documentId, documentNumber, documentDate, amount, customer);
            documentsRef.document(documentId).set(document);
            Toast.makeText(getContext(), "Document Created Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);

        });


        binding.buttonViewDocuments.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ViewDocumentsActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}