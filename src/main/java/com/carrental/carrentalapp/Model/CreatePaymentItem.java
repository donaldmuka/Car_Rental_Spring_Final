package com.carrental.carrentalapp.Model;

import com.google.gson.annotations.SerializedName;


//Payment item for the stripe integration

public class CreatePaymentItem {
    @SerializedName("id")
    String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
