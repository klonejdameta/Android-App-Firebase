package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kmeta.logicalapp.Adapters.DocumentsAdapter;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.R;

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

        DatabaseConnector databaseConnector = new DatabaseConnector(this);
/*        List<DocumentsModel> documentsModels = databaseConnector.getDocumentsList();

        if (documentsModels.size() > 0) {
            DocumentsAdapter documentsAdapter = new DocumentsAdapter(documentsModels, ViewDocumentsActivity.this);
            recyclerView.setAdapter(documentsAdapter);
        } else {
            Toast.makeText(this, "There is no document in the database", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void documentsBackButton(View view) {
        onBackPressed();
    }
}