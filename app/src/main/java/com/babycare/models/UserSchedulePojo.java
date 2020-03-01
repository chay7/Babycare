package com.babycare.models;

import com.google.gson.annotations.SerializedName;

public class UserSchedulePojo {
    @SerializedName("name")
    private String name;

    @SerializedName("phno")
    private String phno;

    @SerializedName("date")
    private String date;

    public UserSchedulePojo(String name, String phno, String date) {
        this.setName(name);
        this.setPhno(phno);
        this.setDate(date);


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
