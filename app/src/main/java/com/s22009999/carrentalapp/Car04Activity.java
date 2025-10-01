package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Car04Activity extends AppCompatActivity {

    Button select;
    TextView carName;

    //For FireBase DataBase
    DatabaseReference reference;
    FirebaseDatabase database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car04);

        String cusEmail = getIntent().getStringExtra("cusEmail");

        //Firebase
        reference = FirebaseDatabase.getInstance().getReference("Customer Details");

        //Get Car name
        carName = findViewById(R.id.carNameFour);
        String CarName = carName.getText().toString();
        // Initialize select button and set click listener
        select = findViewById(R.id.SELECTBTN);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(cusEmail).child("carType").setValue(CarName);
                Intent intent = new Intent(Car04Activity.this, BookingActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }
        });

    }
}