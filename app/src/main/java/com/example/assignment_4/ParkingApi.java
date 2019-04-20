package com.example.assignment_4;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParkingApi {

    @GET("/api/v1/parkinglocations")
    Call<ParkingPojo> getParking();
}
