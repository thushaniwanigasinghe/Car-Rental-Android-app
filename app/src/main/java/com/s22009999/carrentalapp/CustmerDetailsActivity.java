package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustmerDetailsActivity extends AppCompatActivity implements SensorEventListener {

    // UI elements
    TextView name, email, pickAddress, dropAddress, pickupDate, dropDate, driverName, carName, temp;
    Button okBtn, updateBtn, deleteBtn;

    //  Firebase database reference
    DatabaseReference reference;

    //  sensor manager and sensor for temperature
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemperatureSensorAvailable;

    //  variable for customer email
    String cusEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custmer_details);

        //  UI elements
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pickAddress = findViewById(R.id.pickAddress);
        dropAddress = findViewById(R.id.dropAddress);
        pickupDate = findViewById(R.id.pickupDate);
        dropDate = findViewById(R.id.dropDate);
        driverName = findViewById(R.id.driverName);
        carName = findViewById(R.id.carName);
        temp = findViewById(R.id.temp);

        //  sensor manager and temperature sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSensorAvailable = true;
        } else {
            temp.setText("Temperature sensor is not available");
            isTemperatureSensorAvailable = false;
        }

        // Get customer email from the Intent
        cusEmail = getIntent().getStringExtra("cusEmail");

        // Retrieve and display customer data from Firebase
        viewData();

        //  buttons
        okBtn = findViewById(R.id.okBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        // Set click listener for OK button to navigate to AboutUsActivity
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustmerDetailsActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustmerDetailsActivity.this, UpdateCustomerActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustmerDetailsActivity.this, LocationActivity.class);
                startActivity(intent);
                deleteCustomerData();
            }
        });
    }

    // Method to handle sensor data changes
    @Override
    public void onSensorChanged(SensorEvent event) {
        temp.setText(event.values[0] + " °C ");
    }

    // Method to handle sensor accuracy changes (not used here)
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    // Register sensor listener when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        if (isTemperatureSensorAvailable) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // Unregister sensor listener when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        if (isTemperatureSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }

    // Method to retrieve and display customer data from Firebase
    private void viewData() {
        reference = FirebaseDatabase.getInstance().getReference("Customer Details");
        reference.child(cusEmail).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String Cname = String.valueOf(dataSnapshot.child("cusName").getValue());
                        String Cemail = String.valueOf(dataSnapshot.child("email").getValue());
                        String CpickAddress = String.valueOf(dataSnapshot.child("pickupAddress").getValue());
                        String CdropAddress = String.valueOf(dataSnapshot.child("dropAddress").getValue());
                        String CpickupDate = String.valueOf(dataSnapshot.child("pickupDate").getValue());
                        String CdropDate = String.valueOf(dataSnapshot.child("dropDate").getValue());
                        String CdriverName = String.valueOf(dataSnapshot.child("driverName").getValue());
                        String CcarName = String.valueOf(dataSnapshot.child("carType").getValue());
                        name.setText(Cname);
                        email.setText(Cemail);
                        pickAddress.setText(CpickAddress);
                        dropAddress.setText(CdropAddress);
                        pickupDate.setText(CpickupDate);
                        dropDate.setText(CdropDate);
                        driverName.setText(CdriverName);
                        carName.setText(CcarName);
                    } else {
                        Toast.makeText(CustmerDetailsActivity.this, "User Does not Exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CustmerDetailsActivity.this, "Failed To Read Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to delete customer data from Firebase
    private void deleteCustomerData() {
        reference.child(encodeEmail(cusEmail)).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustmerDetailsActivity.this, "Customer Deleted Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CustmerDetailsActivity.this, "Failed to Delete Customer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for Update button to navigate to UpdateCustomerActivity with data
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustmerDetailsActivity.this, UpdateCustomerActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("pickAddress", pickAddress.getText().toString());
                intent.putExtra("dropAddress", dropAddress.getText().toString());
                intent.putExtra("DropDate", dropDate.getText().toString());
                intent.putExtra("PickDate", pickupDate.getText().toString());
                startActivity(intent);
            }
        });
    }

    // Method to encode email by replacing '.' with ',' to use as Firebase key
    private String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}
