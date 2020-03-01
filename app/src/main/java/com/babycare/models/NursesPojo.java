package com.babycare.models;

import com.google.gson.annotations.SerializedName;

public class NursesPojo {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("phone")
        private String phno;

        @SerializedName("emailid")
        private String emailid;

        @SerializedName("uname")
        private String uname;

        @SerializedName("experience")
        private String experience;

        @SerializedName("location")
        private String location;

        @SerializedName("user_type")
        private String user_type;

        @SerializedName("rating")
        private String rating;

    @SerializedName("img_url")
    private String img_url;

        public NursesPojo(String id,String name,String phno,String emailid,String uname,String experience,String location,String user_type,String rating) {
            this.setId(id);
            this.setName(name);
            this.setPhno(phno);
            this.setEmailid(emailid);
            this.setUname(uname);
            this.setLocation(location);
            this.setUser_type(user_type);
            this.setRating(rating);
            this.setExperience(experience);




        }

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

        public String getPhno() {
            return phno;
        }

        public void setPhno(String phno) {
            this.phno = phno;
        }

        public String getEmailid() {
            return emailid;
        }

        public void setEmailid(String emailid) {
            this.emailid = emailid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

   /* @SerializedName("name")
    private String name;

    @SerializedName("phno")
    private String phno;

    @SerializedName("rating")
    private String rating;

    @SerializedName("experience")
    private String experience;



    public NursesPojo(String name, String phno, String rating, String experience) {
        this.setName(name);
        this.setPhno(phno);
        this.setRating(rating);
        this.setExperience(experience);


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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
*/