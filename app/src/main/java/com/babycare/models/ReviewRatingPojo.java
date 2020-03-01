package com.babycare.models;

import com.google.gson.annotations.SerializedName;

public class ReviewRatingPojo {
    @SerializedName("rating")
    private String rating;

    @SerializedName("review")
    private String review;

    @SerializedName("uname")
    private String uname;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
