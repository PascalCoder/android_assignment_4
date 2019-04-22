package com.example.assignment_4;

import android.location.Address;

public class ParkingExtras {

    public Address address;
    public String simpleAddress;
    public int openSpot = 5;
    public String distance;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSimpleAddress() { return simpleAddress; }

    public void setSimpleAddress(String simpleAddress) { this.simpleAddress = simpleAddress; }

    public int getOpenSpot() {
        return openSpot;
    }

    public void setOpenSpot(int openSpot) {
        this.openSpot = openSpot;
    }

    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }
}
