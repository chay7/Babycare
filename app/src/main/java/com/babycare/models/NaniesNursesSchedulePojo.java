package com.babycare.models;

import com.google.gson.annotations.SerializedName;

public class NaniesNursesSchedulePojo {
    @SerializedName("id")
    private String id ;

    @SerializedName("schedule_date")
    private String schedule_date ;

    @SerializedName("name")
    private String name ;

    @SerializedName("phone")
    private String phone ;

    @SerializedName("emailid")
    private String emailid ;

    @SerializedName("img_url")
    private String img_url ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
