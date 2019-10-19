package com.apek.uoas;

public class Battery {

    private String battery, uid, date;
    private Double price;

    public Battery(String uid, String battery, Double price, String date) {

        this.uid = uid;
        this.battery = battery;
        this.price = price;
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
