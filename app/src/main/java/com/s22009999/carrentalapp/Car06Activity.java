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

public class Car06Activity extends AppCompatActivity {

    Button select;
    TextView carName;

    //For FireBase DataBase
    DatabaseReference reference;
    FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car06);

        String cusEmail = getIntent().getStringExtra("cusEmail");

        //Firebase
        reference = FirebaseDatabase.getInstance().getReference("Customer Details");

        //Get Car name
        carName = findViewById(R.id.carNameSix);
        String CarName = carName.getText().toString();

        select = findViewById(R.id.SELECTBTN);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.child(cusEmail).setValue(CarName);
                reference.child(cusEmail).child("carType").setValue(CarName);
                Intent intent = new Intent(Car06Activity.this, BookingActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }
        });

    }
}