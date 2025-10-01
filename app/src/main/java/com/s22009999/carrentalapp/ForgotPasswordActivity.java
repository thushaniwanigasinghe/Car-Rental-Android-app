package com.s22009999.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    //  UI elements
    Button btnReset, btnBack;
    EditText edtEmail;
    ProgressBar progressBar;

    //  Firebase Authentication instance
    FirebaseAuth mAuth;

    String cusEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // UI elements
        btnBack = findViewById(R.id.btnForgotPasswordBack);
        btnReset = findViewById(R.id.btnReset);
        edtEmail = findViewById(R.id.edtForgotPasswordEmail);
        progressBar = findViewById(R.id.forgetPasswordProgressbar);

        // Firebase Authentication instance
        mAuth = FirebaseAuth.getInstance();

        // Set click listener for Reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get email from EditText and trim whitespace
                cusEmail = edtEmail.getText().toString().trim();

                // Check if email field is not empty
                if (!TextUtils.isEmpty(cusEmail)) {
                    // Call method to reset password
                    ResetPassword();
                } else {
                    // Show error if email field is empty
                    edtEmail.setError("Email field can't be empty");
                }
            }
        });

        // Set click listener for Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous activity
                onBackPressed();
            }
        });
    }

    // Method to reset password
    private void ResetPassword() {
        // Show progress bar and hide Reset button
        progressBar.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.INVISIBLE);

        // Send password reset email using Firebase Authentication
        mAuth.sendPasswordResetEmail(cusEmail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // Show success message and navigate to SignInActivity
                        Toast.makeText(ForgotPasswordActivity.this, "Reset Password link has been sent to your registered Email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Show error message and hide progress bar, show Reset button
                        Toast.makeText(ForgotPasswordActivity.this, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        btnReset.setVisibility(View.VISIBLE);
                    }
                });
    }
}
