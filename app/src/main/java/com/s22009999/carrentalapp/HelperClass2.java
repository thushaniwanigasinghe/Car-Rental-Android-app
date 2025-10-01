package com.s22009999.carrentalapp;

public class HelperClass2 {

        private String cusName;
        private String pickupAddress;
        private String dropAddress;
        private String location;
        private double latitude;
        private double longitude;


    public HelperClass2(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;


    }

    public HelperClass2() {
            // Default constructor required for calls to DataSnapshot.getValue(HelperClass.class)
        }

        public HelperClass2(String cusName, String pickupAddress, String dropAddress, String location) {
            this.cusName = cusName;
            this.pickupAddress = pickupAddress;
            this.dropAddress = dropAddress;
            this.location = location;
        }

        public double getLatitude() {
        return latitude;
        }

        public void setLatitude(double latitude) {
        this.latitude = latitude;
         }

        public double getLongitude() {
        return longitude;
         }

        public void setLongitude(double longitude) {
        this.longitude = longitude;
        }

        public String getCusName() {
            return cusName;
        }

        public String getPickupAddress() {
            return pickupAddress;
        }

        public String getDropAddress() {
            return dropAddress;
        }

        public String getLocation() {
            return location;
        }

    public String getKey() {
        return null;
    }
}


