package com.example.assignment_4;

import android.location.Address;

public class ParkingExtras {

    protected Address address;
    protected int openSpot = 5;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getOpenSpot() {
        return openSpot;
    }

    public void setOpenSpot(int openSpot) {
        this.openSpot = openSpot;
    }
}
