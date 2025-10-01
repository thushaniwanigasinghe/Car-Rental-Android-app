package com.s22009999.carrentalapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RentLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Retrieve the layout and initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap){
        myMap = googleMap;
        LatLng tangalle = new LatLng(6.023983481591632, 80.79397560116047);
        myMap.addMarker(new MarkerOptions().position(tangalle).title("SWIFT Car House"));

        //Initialise the map Options
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tangalle,18.0f));
        myMap.getUiSettings().setZoomControlsEnabled(true);
    }
}