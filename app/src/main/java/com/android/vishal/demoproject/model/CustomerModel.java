package com.android.vishal.demoproject.model;

import com.google.gson.annotations.SerializedName;

public class CustomerModel {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("img")
    private String img;

    @SerializedName("gender")
    private String gender;

    @SerializedName("age")
    private int age;

    @SerializedName("date")
    private String date;

    @SerializedName("status")
    private String status;


    //Constructor
    public CustomerModel() {
    }


    //Getter Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
