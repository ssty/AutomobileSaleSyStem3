package com.example.acer.automobilesalesystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("email")
    @Expose
    public String email;

}
