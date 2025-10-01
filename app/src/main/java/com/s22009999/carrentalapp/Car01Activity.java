package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Car01Activity extends AppCompatActivity {

    Button select;
    TextView carName;

    //For FireBase DataBase
    DatabaseReference reference;
    FirebaseDatabase database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car01);

        //Firebase
        reference = FirebaseDatabase.getInstance().getReference("Customer Details");
        // Get customer email from the Intent
        String cusEmail = getIntent().getStringExtra("cusEmail");

        //Get Car name
        carName = findViewById(R.id.carNameOne);
        String CarName = carName.getText().toString();
        // Initialize select button and set click listener
        select = findViewById(R.id.SELECTBTN);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save selected car name to Firebase database under customer details
                reference.child(cusEmail).child("carType").setValue(CarName);
                Toast.makeText(Car01Activity.this, CarName + " Selected.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Car01Activity.this, BookingActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }
        });


    }
}