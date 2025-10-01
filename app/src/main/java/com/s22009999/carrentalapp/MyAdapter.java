package com.s22009999.carrentalapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<HelperClass2> list;

    //For search bookings
    public void setFilteredList(List<HelperClass2> filteredList) {
        this.list = (ArrayList<HelperClass2>) filteredList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, ArrayList<HelperClass2> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HelperClass2 helperClass2 = list.get(position);
        holder.cusName.setText(helperClass2.getCusName());
        holder.pickupAddress.setText(helperClass2.getPickupAddress());
        holder.dropAddress.setText(helperClass2.getDropAddress());
        holder.location.setText(helperClass2.getLocation());

        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("location", helperClass2.getLocation());
                context.startActivity(intent);
            }
        });

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmBooking(helperClass2);
            }
        });

        holder.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AboutUsActivity.class);
                // Pass any necessary data to NextActivity using intent extras
                intent.putExtra("cusName", helperClass2.getCusName());
                intent.putExtra("pickupAddress", helperClass2.getPickupAddress());
                intent.putExtra("dropAddress", helperClass2.getDropAddress());
                intent.putExtra("location", helperClass2.getLocation());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void setFilterdList(List<HelperClass2> filterdList) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cusName, pickupAddress, dropAddress, location;
        Button btnConfirm, btnGo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cusName = itemView.findViewById(R.id.cusName);
            pickupAddress = itemView.findViewById(R.id.pickupAddress);
            dropAddress = itemView.findViewById(R.id.dropAddress);
            location = itemView.findViewById(R.id.location);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            btnGo = itemView.findViewById(R.id.btnGo); // Assuming you have a button with id btnGo in your item layout
        }
    }

    private void confirmBooking(HelperClass2 helperClass2) {
        // Save booking details to Firebase
        DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("Confirmed Bookings").child(helperClass2.getCusName());
        bookingRef.child("cusName").setValue(helperClass2.getCusName());
        bookingRef.child("pickupAddress").setValue(helperClass2.getPickupAddress());
        bookingRef.child("dropAddress").setValue(helperClass2.getDropAddress());
        bookingRef.child("location").setValue(helperClass2.getLocation());

        // Notify the user
        Toast.makeText(context, "Booking Confirmed", Toast.LENGTH_SHORT).show();
    }
}
