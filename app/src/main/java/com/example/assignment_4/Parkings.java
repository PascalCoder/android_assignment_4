package com.example.assignment_4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parkings {

    //@SerializedName("results")
    @Expose
    public List<ParkingPojo> parkingList;
}
