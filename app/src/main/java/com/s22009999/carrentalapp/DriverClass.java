package com.s22009999.carrentalapp;

public class DriverClass {
    String driverName, driverAge;

    public DriverClass() {
    }

    public DriverClass(String driverName, String driverAge) {
        this.driverName = driverName;
        this.driverAge = driverAge;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(String driverAge) {
        this.driverAge = driverAge;
    }
}
