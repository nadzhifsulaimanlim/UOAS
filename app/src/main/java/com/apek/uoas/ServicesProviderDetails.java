package com.apek.uoas;

public class ServicesProviderDetails {
    private String uid, name, email, companyname, companydescription, address, contact, date;

    public ServicesProviderDetails(String uid, String name, String email, String companyname, String companydescription, String address, String contact, String date) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.companyname = companyname;
        this.companydescription = companydescription;
        this.address = address;
        this.contact = contact;
        this.date = date;
    }

    public ServicesProviderDetails() {
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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanydescription() {
        return companydescription;
    }

    public void setCompanydescription(String companydescription) {
        this.companydescription = companydescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


