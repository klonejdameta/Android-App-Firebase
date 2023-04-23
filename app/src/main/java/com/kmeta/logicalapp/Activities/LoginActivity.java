package com.kmeta.logicalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kmeta.logicalapp.MainActivity;
import com.kmeta.logicalapp.databinding.ActivityLoginBinding;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private void handleLoginSuccess(String accessToken, String username) {
        SharedPreferences preferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("access_token", accessToken);
        editor.putString("username", username);
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        binding.loginButton.setOnClickListener(view -> {
            String email = binding.loginEmail.getText().toString();
            String password = binding.loginPassword.getText().toString();
            if (email.equals("") && password.equals("")) {
                Toast.makeText(LoginActivity.this, "Please insert your email and password", Toast.LENGTH_SHORT).show();
            } else if (email.equals("")) {
                Toast.makeText(LoginActivity.this, "Please insert your email", Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(LoginActivity.this, "Please insert your password", Toast.LENGTH_SHORT).show();
            } else {
                db.collection("users")
                        .whereEqualTo("email", email)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                if (task.getResult().size() > 0) {
                                    String username = task.getResult().getDocuments().get(0).getString("username");
                                    mAuth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(LoginActivity.this, task1 -> {
                                                if (task1.isSuccessful()) {
                                                    String accessToken = UUID.randomUUID().toString();
                                                    handleLoginSuccess(accessToken, username);
                                                    Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    intent.putExtra("USER_EMAIL", email);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    Toast.makeText(LoginActivity.this, "This email does not exist in our system", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Error getting user information", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        binding.signupRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}