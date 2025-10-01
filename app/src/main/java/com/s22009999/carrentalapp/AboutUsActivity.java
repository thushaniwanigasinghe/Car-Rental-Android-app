package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {
    private TextView locationLink;
    private TextView setting;
    private Button backButton;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Initialize TextViews for location link and settings
        locationLink = findViewById(R.id.locationLink);
        setting= findViewById(R.id.setting);
        // Set click listener for location link to navigate to RentLocationActivity
        locationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, RentLocationActivity.class);
                startActivity(intent);
            }
        });
        // Set click listener for setting link to navigate to SettingActivity
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        // Initialize Facebook link TextView and set click listener
        findViewById(R.id.facebookLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookPage();
            }
        });

        // Initialize logout button and set click listener
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        // Initialize back button and set click listener to navigate to UserTypeActivity
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, UserTypeActivity.class);
                startActivity(intent);
            }
        });
    }



    private void openFacebookPage() {
        // Replace with your Facebook page URL
        String facebookUrl = "https://www.facebook.com/share/kbatQSTFRs79LDog/?mibextid=qi2Omg";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        startActivity(intent);
    }

    private void logout() {
        // Close the app
        finishAffinity();
    }
}
