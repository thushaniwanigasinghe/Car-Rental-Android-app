package com.s22009999.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserTypeActivity extends AppCompatActivity {
    private Button btnCustomer;
    private Button btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type); // Set the layout for this activity

        //  buttons from the layout
        btnCustomer = findViewById(R.id.btncustomer);
        // Find the customer button
        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When customer button is clicked, start LocationActivity
                Intent intent = new Intent(UserTypeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        btnAdmin = findViewById(R.id.btnadmin);
        // Find the admin button
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When admin button is clicked, start SignupActivity
                Intent intent = new Intent(UserTypeActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
