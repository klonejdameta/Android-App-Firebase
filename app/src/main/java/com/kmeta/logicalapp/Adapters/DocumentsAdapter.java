package com.kmeta.logicalapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.R;

import java.util.List;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder> {
    List<DocumentsModel> documentsModels;
    Context context;
    DatabaseConnector databaseConnector;

    public DocumentsAdapter(List<DocumentsModel> documentsModels, Context context) {
        this.documentsModels = documentsModels;
        this.context = context;
        databaseConnector = new DatabaseConnector(context);
    }

    @NonNull
    @Override
    public DocumentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.documents_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final DocumentsModel documentsModelClass = documentsModels.get(position);

        holder.textViewID.setText(documentsModelClass.getId());
        holder.document_number.setText(documentsModelClass.getDocumentNumber());
        holder.document_date.setText(documentsModelClass.getDocumentDate());
        holder.amount.setText(documentsModelClass.getAmount());
        holder.customer.setText(documentsModelClass.getCustomer());

        holder.buttonEditDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to update this item?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String stringDocumentNumber = holder.document_number.getText().toString();
                                String stringDocumentDate = holder.document_date.getText().toString();
                                String stringAmount = holder.amount.getText().toString();
                                String stringCustomer = holder.customer.getText().toString();
/*                                databaseConnector.updateDocument(new DocumentsModel(documentsModelClass.getId(),
                                        stringDocumentNumber,
                                        stringDocumentDate,
                                        stringAmount,
                                        stringCustomer));
                                notifyDataSetChanged();
                                ((Activity) context).finish();
                                context.startActivity(((Activity) context).getIntent());*/
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        holder.buttonDeleteDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String documentNumber = documentsModelClass.getDocumentNumber();
                String confirmationMessage = "Are you sure you want to delete " + documentNumber + "?";
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage(confirmationMessage)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                /*databaseConnector.deleteDocument(documentsModelClass.getId());
                                documentsModels.remove(position);
                                notifyDataSetChanged();*/
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return documentsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText document_number, document_date, amount, customer;
        Button buttonEditDocument;
        Button buttonDeleteDocument;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            document_number = itemView.findViewById(R.id.document_number);
            document_date = itemView.findViewById(R.id.document_date);
            amount = itemView.findViewById(R.id.document_amount);
            customer = itemView.findViewById(R.id.customer);
            buttonDeleteDocument = itemView.findViewById(R.id.button_delete_document);
            buttonEditDocument = itemView.findViewById(R.id.button_edit_document);

        }
    }
}
