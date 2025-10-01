package com.s22009999.carrentalapp;

public class HelperClass {
    String email, location, pickupAddress, pickupDate, pickupTime, dropAddress, dropDate,
            dropTime, carType, driverName, cusName, cusAddress, cusContact;

    public HelperClass(String email, String location, String pickupAddress, String pickupDate,
                       String pickupTime, String dropAddress, String dropDate, String dropTime,
                       String carType, String driverName, String cusName, String cusAddress, String cusContact) {
        this.email = email;
        this.location = location;
        this.pickupAddress = pickupAddress;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.dropAddress = dropAddress;
        this.dropDate = dropDate;
        this.dropTime = dropTime;
        this.carType = carType;
        this.driverName = driverName;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusContact = cusContact;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getDropDate() {
        return dropDate;
    }

    public void setDropDate(String dropDate) {
        this.dropDate = dropDate;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }
    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusContact() {
        return cusContact;
    }

    public void setCusContact(String cusContact) {
        this.cusContact = cusContact;
    }

}
