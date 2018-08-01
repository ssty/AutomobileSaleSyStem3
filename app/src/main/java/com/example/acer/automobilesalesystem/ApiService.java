package com.example.acer.automobilesalesystem;

import com.example.acer.automobilesalesystem.models.LoginResponseModel;
import com.example.acer.automobilesalesystem.models.VehicleResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("vehicles")
    Call<ArrayList<VehicleResponseModel>> getVehicleList();

    @GET("api/users")
    Call<ArrayList<LoginResponseModel>>  getUserList();

}