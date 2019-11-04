package com.Yam.ESwim;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Product implements Serializable{

    @Exclude private String id;

    private String name, age, bestswim;
    private String comp;
    private String agegroup;

    public Product() {

    }

    public Product(String name, String age, String bestswim, String comp, String agegroup) {
        this.name = name;
        this.age = age;
        this.bestswim = bestswim;
        this.comp = comp;
        this.agegroup = agegroup;
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

    public String getBestSwim() {

        return bestswim;
    }

    public String getComp() {

        return comp;
    }

    public String getAgegroup() {

        return agegroup;
    }

}
