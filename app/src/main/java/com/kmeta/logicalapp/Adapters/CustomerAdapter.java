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

import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.R;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    List<CustomerModel> customerModels;
    Context context;
    DatabaseConnector databaseConnector;

    public CustomerAdapter(List<CustomerModel> customerModels, Context context) {
        this.customerModels = customerModels;
        this.context = context;
        databaseConnector = new DatabaseConnector(context);
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CustomerModel customerModelClass = customerModels.get(position);

        holder.textViewID.setText(Integer.toString(customerModelClass.getId()));
        holder.editText_firstName.setText(customerModelClass.getFirstName());
        holder.editText_lastName.setText(customerModelClass.getLastName());
        holder.editText_birthDate.setText(customerModelClass.getBirthDate());
        holder.editText_address.setText(customerModelClass.getAddress());
        holder.editText_longitude.setText(customerModelClass.getLongitude());
        holder.editText_latitude.setText(customerModelClass.getLatitude());
        holder.editText_isActive.setText(customerModelClass.getIsActive());

        holder.buttonEditCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to update this item?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String stringFirstName = holder.editText_firstName.getText().toString();
                                String stringLastName = holder.editText_lastName.getText().toString();
                                String stringBirthDate = holder.editText_birthDate.getText().toString();
                                String stringAddress = holder.editText_address.getText().toString();
                                String stringLatitude = holder.editText_longitude.getText().toString();
                                String stringLongitude = holder.editText_latitude.getText().toString();
                                String stringIsActive = holder.editText_isActive.getText().toString();

/*                                databaseConnector.updateCustomer(new CustomerModel(customerModelClass.getId(),
                                        stringFirstName,
                                        stringLastName,
                                        stringBirthDate,
                                        stringAddress,
                                        stringLatitude,
                                        stringLongitude,
                                        stringIsActive));
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

        holder.buttonDeleteCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = customerModelClass.getFirstName(); // get the customer name
                String confirmationMessage = "Are you sure you want to delete " + customerName + "?"; // concatenate the name with the confirmation message
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage(confirmationMessage)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
/*
                                databaseConnector.deleteCustomer(customerModelClass.getId());
                                customerModels.remove(position);
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
        return customerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
