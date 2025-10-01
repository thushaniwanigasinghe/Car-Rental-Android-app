package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateCustomerActivity extends AppCompatActivity {

    EditText editName, editEmail, editPickAddress, editDropAddress, editDropDate, editPickDate;
    Button saveBtn;
    DatabaseReference reference;
    String cusEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPickAddress = findViewById(R.id.editPickAddress);
        editDropAddress = findViewById(R.id.editDropAddress);
        editDropDate = findViewById(R.id.editDropDate);
        editPickDate = findViewById(R.id.editPickDate);
        saveBtn = findViewById(R.id.saveBtn);

        // Get data from intent
        cusEmail = getIntent().getStringExtra("cusEmail");

        // Populate the fields with existing data
        editName.setText(getIntent().getStringExtra("name"));
        editEmail.setText(getIntent().getStringExtra("email"));
        editPickAddress.setText(getIntent().getStringExtra("pickAddress"));
        editDropAddress.setText(getIntent().getStringExtra("dropAddress"));
        editDropDate.setText(getIntent().getStringExtra("DropDate"));
        editPickDate.setText(getIntent().getStringExtra("PickDate"));

        reference = FirebaseDatabase.getInstance().getReference("Customer Details");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCustomerData();
            }
        });
    }

    private void updateCustomerData() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String pickAddress = editPickAddress.getText().toString();
        String dropAddress = editDropAddress.getText().toString();
        String DropDate = editDropDate.getText().toString();
        String PickDate = editPickDate.getText().toString();

        reference.child(cusEmail).child("cusName").setValue(name);
        reference.child(cusEmail).child("driverName").setValue(email);
        reference.child(cusEmail).child("pickupAddress").setValue(pickAddress);
        reference.child(cusEmail).child("dropAddress").setValue(dropAddress);
        reference.child(cusEmail).child("DropDate").setValue(DropDate);
        reference.child(cusEmail).child("PickDate").setValue(PickDate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateCustomerActivity.this, "Customer Updated Successfully", Toast.LENGTH_SHORT).show();
                            finish(); // Close the activity after saving
                        } else {
                            Toast.makeText(UpdateCustomerActivity.this, "Failed to Update Customer", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
