package com.s22009999.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseDriverActivity extends AppCompatActivity {
    private Button withbtn;
    private Button withoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_driver);

        //get customer email in Location Activity
        String cusEmail = getIntent().getStringExtra("cusEmail");
        // Initialize Button for choosing with driver and set click listener
        withbtn=findViewById(R.id.withbtn);
        withbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseDriverActivity.this, WithDriverActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }
        });

        withoutbtn = findViewById(R.id.withoutbtn);
        withoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseDriverActivity.this, TypeOfCarActivity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }
        });
    }

}



