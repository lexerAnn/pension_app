package com.android.pension_app.data.remote;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
// Add other fields as needed
}
