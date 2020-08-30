package com.android.vishal.demoproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListResponse {

    @SerializedName("list")
    private ArrayList<CustomerModel> list;

    public ListResponse() {
    }

    public ArrayList<CustomerModel> getList() {
        return list;
    }

    public void setList(ArrayList<CustomerModel> list) {
        this.list = list;
    }
}
