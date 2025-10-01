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

public class WithDriverActivity extends AppCompatActivity {

    private Button fistDriverAdd, secDriverAdd;
    TextView fistDriverName, fistDriverAge, secDriverName, secDriverAge;

    // For Firebase Database
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_driver);

        //  Firebase Database reference
        reference = FirebaseDatabase.getInstance().getReference("Customer Details");

        // Get customer email passed from LocationActivity
        String cusEmail = getIntent().getStringExtra("cusEmail");

        //  TextViews for driver details
        fistDriverName = findViewById(R.id.fistDrivername);
        fistDriverAge = findViewById(R.id.fistDriverAge);
        secDriverName = findViewById(R.id.secDriverName);
        secDriverAge = findViewById(R.id.secDriverAge);

        //  Buttons for adding drivers
        fistDriverAdd = findViewById(R.id.fistDriverAdd);
        fistDriverAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get first driver's name from EditText
                String driverName = fistDriverName.getText().toString();

                // Insert data into Firebase
                reference.child(cusEmail).child("driverName").setValue(driverName);

                // Display toast message
                Toast.makeText(WithDriverActivity.this, driverName + " Added.", Toast.LENGTH_SHORT).show();

                // Start TypeOfCarActivity after adding driver
                Intent intent = new Intent(WithDriverActivity.this, TypeOfCarActivity.class);
                intent.putExtra("cusEmail", cusEmail); // Pass customer email to next activity
                startActivity(intent);
            }
        });

        secDriverAdd = findViewById(R.id.secDriverAdd);
        secDriverAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get second driver's name from EditText
                String driverNameTwo = secDriverName.getText().toString();

                // Insert data into Firebase
                reference.child(cusEmail).child("driverName").setValue(driverNameTwo);

                // Display toast message
                Toast.makeText(WithDriverActivity.this, driverNameTwo + " Added.", Toast.LENGTH_SHORT).show();

                // Start TypeOfCarActivity after adding driver
                Intent intent = new Intent(WithDriverActivity.this, TypeOfCarActivity.class);
                intent.putExtra("cusEmail", cusEmail); // Pass customer email to next activity
                startActivity(intent);
            }
        });
    }
}
