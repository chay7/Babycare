package com.babycare.models;

import com.google.gson.annotations.SerializedName;

public class AdminEditProfilePojo {
    @SerializedName("name")
    private String name ;

    @SerializedName("phone")
    private
    String phone ;

    @SerializedName("emailid")
    private
    String emailid ;


    @SerializedName("pwd")
    private String pwd ;

    @SerializedName("experience")
    private String experience ;

    @SerializedName("location")
    private String location ;





    AdminEditProfilePojo(String name, String phone, String emailid, String pwd,String experience,String location){

        this.setName(name);
        this.setPhone(phone);
        this.setEmailid(emailid);
        this.setPwd(pwd);
        this.setExperience(experience);
        this.setLocation(location);
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
