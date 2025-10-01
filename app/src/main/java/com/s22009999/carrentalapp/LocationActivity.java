package com.s22009999.carrentalapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class LocationActivity extends AppCompatActivity {

    EditText cusPickAddress, cusPickDate, cusPickTime, cusDropAddress, cusDropDate, cusDropTime, cusEmail, cusLocation;
    private Button customerNext;

    // For FireBase DataBase
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Customer Details");

        // Get reference to edit texts
        cusPickAddress = findViewById(R.id.cusPickAddress);
        cusPickDate = findViewById(R.id.cusPickDate);
        cusPickTime = findViewById(R.id.cusPickTime);
        cusDropAddress = findViewById(R.id.cusDropAddress);
        cusDropDate = findViewById(R.id.cusDropDate);
        cusDropTime = findViewById(R.id.cusDropTime);
        cusEmail = findViewById(R.id.cusEmail);
        cusLocation = findViewById(R.id.cusLocation);

        // Set up date and time pickers
        cusPickDate.setOnClickListener(v -> showDatePickerDialog(cusPickDate));
        cusPickTime.setOnClickListener(v -> showTimePickerDialog(cusPickTime));
        cusDropDate.setOnClickListener(v -> showDatePickerDialog(cusDropDate));
        cusDropTime.setOnClickListener(v -> showTimePickerDialog(cusDropTime));

        // Next Button
        customerNext = findViewById(R.id.customerNext);
        customerNext.setOnClickListener(v -> {
            // User inputs to String
            String emailBefore = cusEmail.getText().toString();
            String email = encodeEmail(emailBefore);
            String location = cusLocation.getText().toString();
            String pickAddress = cusPickAddress.getText().toString();
            String pickDate = cusPickDate.getText().toString();
            String pickTime = cusPickTime.getText().toString();
            String dropAddress = cusDropAddress.getText().toString();
            String dropDate = cusDropDate.getText().toString();
            String dropTime = cusDropTime.getText().toString();

            // Insert Data To Firebase Database
            HelperClass helperClass = new HelperClass(email, location, pickAddress, pickDate, pickTime, dropAddress, dropDate, dropTime, "null", "null", "null", "null", "null");
            reference.child(email).setValue(helperClass);
            Toast.makeText(LocationActivity.this, "Data Recorded.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LocationActivity.this, ChooseDriverActivity.class);
            // Pass Email to next activity (ChooseDriverActivity)
            intent.putExtra("cusEmail", email);
            startActivity(intent);
        });
    }

    // Show DatePickerDialog
    private void showDatePickerDialog(EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> editText.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    // Show TimePickerDialog
    private void showTimePickerDialog(EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute1) -> editText.setText(hourOfDay + ":" + minute1),
                hour, minute, true);
        timePickerDialog.show();
    }

    // For encode Email
    static String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}
