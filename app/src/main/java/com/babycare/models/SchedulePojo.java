package com.babycare.models;


import com.google.gson.annotations.SerializedName;

public class SchedulePojo {
    @SerializedName("id")
    private String id ;

    @SerializedName("schedule_date")
    private String schedule_date ;

    @SerializedName("nanies_nurse_name")
    private String nanies_nurse_name ;

    @SerializedName("status")
    private String status ;

    @SerializedName("nanies_nurse_phone")
    private String nanies_nurse_phone ;

    @SerializedName("nanies_nurse_email")
    private String nanies_nurse_email ;

    @SerializedName("user_type")
    private String user_type ;

    @SerializedName("nanie_nurse_uname")
    private String nanie_nurse_uname ;

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

    public String getNanies_nurse_name() {
        return nanies_nurse_name;
    }

    public void setNanies_nurse_name(String nanies_nurse_name) {
        this.nanies_nurse_name = nanies_nurse_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNanies_nurse_phone() {
        return nanies_nurse_phone;
    }

    public void setNanies_nurse_phone(String nanies_nurse_phone) {
        this.nanies_nurse_phone = nanies_nurse_phone;
    }

    public String getNanies_nurse_email() {
        return nanies_nurse_email;
    }

    public void setNanies_nurse_email(String nanies_nurse_email) {
        this.nanies_nurse_email = nanies_nurse_email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getNanie_nurse_uname() {
        return nanie_nurse_uname;
    }

    public void setNanie_nurse_uname(String nanie_nurse_uname) {
        this.nanie_nurse_uname = nanie_nurse_uname;
    }
}

