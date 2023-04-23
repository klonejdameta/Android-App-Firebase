package com.kmeta.logicalapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kmeta.logicalapp.MainActivity;
import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.Database.DatabaseConnector;
import com.kmeta.logicalapp.Activities.ViewCustomersActivity;
import com.kmeta.logicalapp.databinding.FragmentCustomersBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomersFragment extends Fragment {
    FragmentCustomersBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    CollectionReference customersRef;

    public CustomersFragment() {

    }

    public static CustomersFragment newInstance(String param1, String param2) {
        CustomersFragment fragment = new CustomersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomersBinding.inflate(inflater, container, false);
        customersRef = FirebaseFirestore.getInstance().collection("customers");
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringFirstName = binding.firstName.getText().toString();
                if (TextUtils.isEmpty(stringFirstName)) {
                    Toast.makeText(getActivity(), "Please provide the first name", Toast.LENGTH_SHORT).show();
                    return;
                }

                String stringLastName = binding.lastName.getText().toString();
                if (TextUtils.isEmpty(stringLastName)) {
                    Toast.makeText(getActivity(), "Please provide the last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                String stringBirthDate = binding.birthDate.getText().toString();
                if (TextUtils.isEmpty(stringBirthDate)) {
                    Toast.makeText(getActivity(), "Please enter your birthdate", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                sdf.setLenient(false);

                try {
                    Date date = sdf.parse(stringBirthDate);

                } catch (ParseException e) {

                    Toast.makeText(getActivity(), "Please enter a valid birth date (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                    return;
                }

                String stringAddress = binding.address.getText().toString();
                if (TextUtils.isEmpty(stringAddress)) {
                    Toast.makeText(getActivity(), "Please enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }

                String stringLongitude = binding.longitude.getText().toString();
                binding.longitude.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                if (TextUtils.isEmpty(stringLongitude)) {

                    Toast.makeText(getActivity(), "Longitude is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!stringLongitude.matches("-?\\d+(\\.\\d+)?")) {

                    Toast.makeText(getActivity(), "Invalid longitude value", Toast.LENGTH_SHORT).show();
                    return;
                }
                double longitude = Double.parseDouble(stringLongitude);
                if (longitude < -90 || longitude > 90) {

                    Toast.makeText(getActivity(), "Invalid longitude value", Toast.LENGTH_SHORT).show();
                    return;
                }

                String stringLatitude = binding.latitude.getText().toString();
                binding.latitude.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                if (TextUtils.isEmpty(stringLatitude)) {

                    Toast.makeText(getActivity(), "Latitude is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!stringLatitude.matches("-?\\d+(\\.\\d+)?")) {

                    Toast.makeText(getActivity(), "Invalid latitude value", Toast.LENGTH_SHORT).show();
                    return;
                }
                double latitude = Double.parseDouble(stringLatitude);
                if (latitude < -90 || latitude > 90) {

                    Toast.makeText(getActivity(), "Invalid latitude value", Toast.LENGTH_SHORT).show();
                    return;
                }
                String isActive = "true";

                if (TextUtils.isEmpty(stringFirstName) ||
                        TextUtils.isEmpty(stringLastName) ||
                        TextUtils.isEmpty(stringBirthDate) ||
                        TextUtils.isEmpty(stringAddress) ||
                        TextUtils.isEmpty(stringLongitude) ||
                        TextUtils.isEmpty(stringLatitude)) {
                    Toast.makeText(getActivity(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    String customerId = customersRef.document().getId();
                    CustomerModel customer = new CustomerModel(customerId, stringFirstName, stringLastName, stringBirthDate, stringAddress, stringLongitude, stringLatitude, isActive);
                    customersRef.document(customerId).set(customer);
                    Toast.makeText(getContext(), "Customer Created Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        binding.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewCustomersActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}