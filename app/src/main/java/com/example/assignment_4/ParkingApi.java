package com.example.assignment_4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParkingApi {

    @GET("api/v1/parkinglocations/search?lat=33.756898&lng=-84.392066")
    Call<List<ParkingPojo>> getParking();
}
