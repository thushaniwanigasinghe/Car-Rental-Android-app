package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<HelperClass2> list;
    MyAdapter myAdapter;
    ValueEventListener eventListener;

    //for search bookings
    androidx.appcompat.widget.SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recycleView);
        // Reference to Firebase database
        database = FirebaseDatabase.getInstance().getReference("Customer Details");
        // RecyclerView has fixed size for performance optimization
        recyclerView.setHasFixedSize(true);
        // Set LinearLayoutManager for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ArrayList to store data from Firebase
        list = new ArrayList<>();
        // Create an instance of your custom adapter
        myAdapter = new MyAdapter(UserListActivity.this, list);
        // Set the adapter for RecyclerView
        recyclerView.setAdapter(myAdapter);

        // Read data from Firebase
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear the list before adding new data
                list.clear();

                // Iterate through each snapshot of data from Firebase
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Convert the snapshot to HelperClass2 object
                    HelperClass2 helperClass2 = dataSnapshot.getValue(HelperClass2.class);
                    // Add HelperClass2 object to the list
                    list.add(helperClass2);
                }
                // Notify adapter of data change
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });

        //for search bookings
        searchView = findViewById(R.id.bookingSearch);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter by customer name
                List<HelperClass2> filterdList = new ArrayList<>();
                for (HelperClass2 item : list){
                    if (item.getCusName().toLowerCase().contains(newText.toLowerCase())){
                        filterdList.add(item);
                    }
                }
                if (filterdList.isEmpty()){
                    Toast.makeText(UserListActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }else {
                    myAdapter.setFilterdList(filterdList);
                }
                return true;
            }
        });

    }
}
