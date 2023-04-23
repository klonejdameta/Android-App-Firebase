package com.kmeta.logicalapp.Adapters;

import android.annotation.SuppressLint;
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
import com.kmeta.logicalapp.Models.UsersModel;
import com.kmeta.logicalapp.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<UsersModel> usersModels;
    Context context;
    DatabaseConnector databaseConnector;

    public UsersAdapter(List<UsersModel> usersModels, Context context) {
        this.usersModels = usersModels;
        this.context = context;
        databaseConnector = new DatabaseConnector(context);
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.users_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final UsersModel usersModel = usersModels.get(position);

        holder.textViewID.setText(usersModel.getId());
        holder.name.setText(usersModel.getName());
        holder.userName.setText(usersModel.getUsername());
        holder.email.setText(usersModel.getEmail());

        holder.buttonEditUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to update this user?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String name = holder.name.getText().toString();
                                String userName = holder.userName.getText().toString();
                                String email = holder.email.getText().toString();
/*                                databaseConnector.updateUsers(new UsersModel(usersModel.getId(),
                                        name,
                                        userName,
                                        email));
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

        holder.buttonDeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usersModel.getName();
                String confirmationMessage = "Are you sure you want to delete " + name + "?";
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage(confirmationMessage)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
/*                                databaseConnector.deleteUsers(usersModel.getId());
                                usersModels.remove(position);
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
        return usersModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText name, userName, email;
        Button buttonEditUsers;
        Button buttonDeleteUsers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id_users);
            name = itemView.findViewById(R.id.name);
            userName = itemView.findViewById(R.id.user_name);
            email = itemView.findViewById(R.id.email);

            buttonEditUsers = itemView.findViewById(R.id.button_edit_users);
            buttonDeleteUsers = itemView.findViewById(R.id.button_delete_users);

        }
    }
}
