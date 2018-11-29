package com.example.user.gymapp;

public class Course {
    private String name;
    private String couch;
    private String phone;

    public Course(String name,String couch,String phone){
        this.name=name;
        this.couch=couch;
        this.phone=phone;
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

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }
}
