package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingActivity extends AppCompatActivity {

    EditText cusName, cusAddress, cusContact;
    Button addBtn;
    DatabaseReference reference;
    FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize Firebase reference to "Customer Details" node
        reference = FirebaseDatabase.getInstance().getReference("Customer Details");
        // Get customer email from the Intent
        String cusEmail = getIntent().getStringExtra("cusEmail");

        // Initialize EditText fields
        cusName  = findViewById(R.id.cusName);
        cusAddress  = findViewById(R.id.cusAddress);
        cusContact  = findViewById(R.id.cusContact);


        addBtn = findViewById(R.id.addBtn);
        // Initialize add button and set click listener
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get customer details from EditText fields
                String cName = cusName.getText().toString();
                String cAddress = cusAddress.getText().toString();
                String cContact = cusContact.getText().toString();

                // Add customer details to the Firebase database
                reference.child(cusEmail).child("cusName").setValue(cName);
                reference.child(cusEmail).child("cusAddress").setValue(cAddress);
                reference.child(cusEmail).child("cusContact").setValue(cContact);
                Toast.makeText(BookingActivity.this, " Saved..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookingActivity.this, CustmerDetailsActivity.class);
                intent.putExtra("cusEmail",cusEmail);
                startActivity(intent);
            }
        });

    }
}