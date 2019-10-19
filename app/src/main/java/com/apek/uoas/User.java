package com.apek.uoas;

public class User {
    private String uid, name, email, carmodel, carplatenumber, year, date;

    public User(String uid, String name, String email, String carmodel, String carplatenumber, String year, String date) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.carmodel = carmodel;
        this.carplatenumber = carplatenumber;
        this.year = year;
        this.date = date;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCarplatenumber() {
        return carplatenumber;
    }

    public void setCarplatenumber(String carplatenumber) {
        this.carplatenumber = carplatenumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
