package com.example.acer.automobilesalesystem;

import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 5/22/2018.
 */

public class User
{
    @SerializedName("user_name")
    private String Response;
    private String Name;

    public String getName() {
        return Name;
    }
}
