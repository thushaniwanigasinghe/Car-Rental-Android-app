package com.s22009999.carrentalapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Declare variables for Google Map, latitude, longitude, and location string
    private GoogleMap myMap;
    private double latitude;
    private double longitude;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Retrieve the location data from the Intent
        location = getIntent().getStringExtra("location");

        // Retrieve the SupportMapFragment and get notified
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //  Geocoder to convert the location name to coordinates
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        try {
            // Get the coordinates from the location name
            addressList = geocoder.getFromLocationName(location, 1);
            if (addressList != null && !addressList.isEmpty()) {
                // If address list is not empty, set latitude and longitude
                latitude = addressList.get(0).getLatitude();
                longitude = addressList.get(0).getLongitude();
            }
        } catch (IOException e) {
            // Handle exception and show a Toast message
            e.printStackTrace();
            Toast.makeText(this, "Can't get location", Toast.LENGTH_SHORT).show();
        }

        // Assign the Google Map object to the class variable
        myMap = googleMap;

        LatLng latLng = new LatLng(latitude, longitude);

        myMap.addMarker(new MarkerOptions().position(latLng).title(location));

        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));

        // Enable zoom controls on the map
        myMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
