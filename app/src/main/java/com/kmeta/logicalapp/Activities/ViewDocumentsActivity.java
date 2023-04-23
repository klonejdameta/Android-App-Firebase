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
import com.kmeta.logicalapp.Adapters.DocumentsAdapter;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewDocumentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_documents);

        recyclerView = findViewById(R.id.recyclerViewDocuments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("documents")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentsModel> documentsModels = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                DocumentsModel documentModel = document.toObject(DocumentsModel.class);
                                documentModel.setId(document.getId());
                                documentsModels.add(documentModel);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (documentsModels.size() > 0) {
                            DocumentsAdapter documentAdapter = new DocumentsAdapter(documentsModels, ViewDocumentsActivity.this);
                            recyclerView.setAdapter(documentAdapter);
                        } else {
                            Toast.makeText(this, "There is no document in the database", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Error getting documents: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void documentsBackButton(View view) {
        onBackPressed();
    }
}