package com.kmeta.logicalapp.Adapters;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    List<CustomerModel> customerModels;
    Context context;

    public CustomerAdapter(List<CustomerModel> customerModels, Context context) {
        this.customerModels = customerModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_item_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CustomerModel customerModelClass = customerModels.get(position);

        holder.textViewID.setText(customerModelClass.getId());
        holder.editText_firstName.setText(customerModelClass.getFirstName());
        holder.editText_lastName.setText(customerModelClass.getLastName());
        holder.editText_birthDate.setText(customerModelClass.getBirthDate());
        holder.editText_address.setText(customerModelClass.getAddress());
        holder.editText_longitude.setText(customerModelClass.getLongitude());
        holder.editText_latitude.setText(customerModelClass.getLatitude());
        holder.editText_isActive.setText(customerModelClass.getIsActive());

        holder.buttonEditCustomers.setOnClickListener(v -> new AlertDialog.Builder(context)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to update this item?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    String stringFirstName = holder.editText_firstName.getText().toString();
                    String stringLastName = holder.editText_lastName.getText().toString();
                    String stringBirthDate = holder.editText_birthDate.getText().toString();
                    String stringAddress = holder.editText_address.getText().toString();
                    String stringLatitude = holder.editText_longitude.getText().toString();
                    String stringLongitude = holder.editText_latitude.getText().toString();
                    String stringIsActive = holder.editText_isActive.getText().toString();

                    String customerId = customerModelClass.getId();
                    DocumentReference customerRef = FirebaseFirestore.getInstance().collection("customers").document(customerId);

                    Map<String, Object> updates = new HashMap<>();
                    updates.put("firstName", stringFirstName);
                    updates.put("lastName", stringLastName);
                    updates.put("birthDate", stringBirthDate);
                    updates.put("address", stringAddress);
                    updates.put("latitude", stringLatitude);
                    updates.put("longitude", stringLongitude);
                    updates.put("isActive", stringIsActive);
                    customerRef.update(updates);

                    notifyDataSetChanged();
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());

        holder.buttonDeleteCustomers.setOnClickListener(v -> {
            String customerName = customerModelClass.getFirstName();
            String confirmationMessage = "Are you sure you want to delete " + customerName + "?";
            new AlertDialog.Builder(context)
                    .setTitle("Confirmation")
                    .setMessage(confirmationMessage)
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("customers").document(customerModelClass.getId()).delete()
                                .addOnSuccessListener(aVoid -> {
                                    customerModels.remove(position);
                                    notifyDataSetChanged();
                                })
                                .addOnFailureListener(e -> Log.w(TAG, "Error deleting document", e));
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return customerModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText editText_firstName, editText_lastName, editText_address, editText_birthDate,
                editText_longitude, editText_latitude, editText_isActive;
        Button buttonEditCustomers;
        Button buttonDeleteCustomers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_firstName = itemView.findViewById(R.id.firstName);
            editText_lastName = itemView.findViewById(R.id.lastName);
            editText_address = itemView.findViewById(R.id.address);
            editText_birthDate = itemView.findViewById(R.id.birthDate);
            editText_longitude = itemView.findViewById(R.id.longitude);
            editText_latitude = itemView.findViewById(R.id.latitude);
            editText_isActive = itemView.findViewById(R.id.isActive);
            buttonDeleteCustomers = itemView.findViewById(R.id.button_delete);
            buttonEditCustomers = itemView.findViewById(R.id.button_edit);

        }
    }
}
