package com.example.acer.automobilesalesystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleResponseModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("vehicle_type")
    @Expose
    public String vehicleType;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("model_no")
    @Expose
    public String modelNo;
    @SerializedName("engine_power")
    @Expose
    public String enginePower;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
}
