package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TypeOfCarActivity extends AppCompatActivity {
    private ImageButton car01;
    private ImageButton car02;
    private ImageButton   car03;
    private ImageButton   car04;
    private ImageButton   car05;
    private ImageButton   car06;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_car);

        //get customer email in Location Activity
        String cusEmail = getIntent().getStringExtra("cusEmail");

        car01 = findViewById(R.id.car01);
        car01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //reference.child(cusEmail).setValue("Car One");
                    Intent intent = new Intent(TypeOfCarActivity.this, Car01Activity.class);
                    intent.putExtra("cusEmail", cusEmail);
                    startActivity(intent);
                }

            });
        car02 = findViewById(R.id.car02);
        car02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.child(cusEmail).setValue("Car Two");
                Intent intent = new Intent(TypeOfCarActivity.this, Car02Activty.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }

        });
        car03 = findViewById(R.id.car03);
        car03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.child(cusEmail).setValue("Car Three");
                Intent intent = new Intent(TypeOfCarActivity.this, Car03Activity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }

        });
        car04 = findViewById(R.id.car04);
        car04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //reference.child(cusEmail).setValue("Car Four");
                Intent intent = new Intent(TypeOfCarActivity.this, Car04Activity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }

        });
        car05 = findViewById(R.id.car05);
        car05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.child(cusEmail).setValue("Car Five");
                Intent intent = new Intent(TypeOfCarActivity.this, Car05Activity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }

        });
        car06 = findViewById(R.id.car06);
        car06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.child(cusEmail).setValue("Car Six");
                Intent intent = new Intent(TypeOfCarActivity.this, Car06Activity.class);
                intent.putExtra("cusEmail", cusEmail);
                startActivity(intent);
            }

        });

    }
}
