package com.example.user.gymapp;

public class Course {
    private String name;
    private String couch;

    public Course(String name,String couch){
        this.name=name;
        this.couch=couch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCouch() {
        return couch;
    }

    public void setCouch(String couch) {
        this.couch = couch;
    }
}
