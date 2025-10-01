package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    EditText loginName, loginPassword;// Declare UI elements
    Button loginButton;
    TextView forgotPasswordTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        // Initialize UI elements
        loginName = findViewById(R.id.loginName);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordTextView = findViewById(R.id.forgot_password);
        // Set click listener for the Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate user input
                if (!validateUsername() | !validatePassword()) {
                    // If validation fails, do nothing
                } else {
                    // If validation succeeds, check user credentials
                    checkUser();
                }
            }
        }); // forgot password
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername() {
        // Method to validate the username
        String val = loginName.getText().toString();
        if (val.isEmpty()) {
            loginName.setError("Username cannot be empty");
            return false;
        } else {
            loginName.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        // Method to validate the password
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }


    // Method to check user credentials against the database
    public void checkUser(){
        String userUsername = loginName.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        // Reference to the Firebase database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ADMIN");
        // Query to find the user by username
        Query checkUserDatabase = reference.orderByChild("signUsername").equalTo(userUsername);
        // Add a listener to handle the query result
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if the user exists
                if (snapshot.exists()){
                    // Get the password from the database
                    loginName.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("signPassword").getValue(String.class);
                    // Check if the password matches
                    if (passwordFromDB.equals(userPassword)) {
                        loginName.setError(null);

                        // Get the email from the database
                        String emailFromDB = snapshot.child(userUsername).child("signEmail").getValue(String.class);
                        String nameFromDB = snapshot.child(userUsername).child("signUsername").getValue(String.class);




                        // Create an intent to start the next activity
                        Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
                        // Pass the user data to the next activity
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        // Start the next activity
                        startActivity(intent);
                    } else {
                        // Show error if the password is incorrect
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    // Show error if the user does not exist
                    loginName.setError("User does not exist");
                    loginName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle potential errors
            }
        });
    }
}