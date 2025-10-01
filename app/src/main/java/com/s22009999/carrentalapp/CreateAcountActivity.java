package com.s22009999.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAcountActivity extends AppCompatActivity {

    EditText signUsername, signEmail, signPassword,signRePassword; // declare ui elements
    private Button buttonSignUp;
    //firebase database reference
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);

        // UI elements
        signUsername = findViewById(R.id.signUsername);
        signEmail = findViewById(R.id.signEmail);
        signPassword = findViewById(R.id.signPassword);
        signRePassword = findViewById(R.id.signRePassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        //set click listener for the sign up buttton
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User inputs to String
                String email = signEmail.getText().toString();
                String user_name = signUsername.getText().toString();
                String password = signPassword.getText().toString();
                String RePassword = signRePassword.getText().toString();

                // Validate input fields
                if (TextUtils.isEmpty(user_name)) {
                    signUsername.setError("Username cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    signEmail.setError("Email cannot be empty");
                    return;
                }
               if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    signEmail.setError("Invalid email format");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    signPassword.setError("Password cannot be empty");
                    return;
                }
                if (password.length() < 6) {
                    signPassword.setError("Password should be at least 6 characters long");
                    return;
                }
                if (password.length() < 6) {
                    signRePassword.setError("Password should be same password");
                    return;
                }
                //Initialize firebase database instance and reference
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("ADMIN");
                 //create a new user object
                SingupClass signupclass= new SingupClass(user_name, email, password, RePassword);
                //store user information in the database
                reference.child(user_name).setValue(signupclass);
                //display success message
                Toast.makeText(CreateAcountActivity.this, "sign up successfully.", Toast.LENGTH_SHORT).show();
                 //redirect to login activity
                Intent intent = new Intent(CreateAcountActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
