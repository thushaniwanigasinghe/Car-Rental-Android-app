package com.s22009999.carrentalapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Hide default title
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Replace settings container with SettingsFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Go back to previous activity
        onBackPressed();
        return true;
    }
}