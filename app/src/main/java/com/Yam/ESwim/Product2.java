package com.Yam.ESwim;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Product2 implements Serializable{

    @Exclude
    private String id;

    private String name, age, venue;
    private String date;
    private String time, status;

    public Product2() {

    }

    public Product2(String name, String age, String venue,String status, String date, String time) {
        this.name = name;
        this.age = age;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.status = status;
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

    public String getAge() {

        return age;
    }
    public String getStatus() {

        return status;
    }

    public String getVenue() {

        return venue;
    }

    public String getDate() {

        return date;
    }

    public String getTime() {

        return time;
    }

}
