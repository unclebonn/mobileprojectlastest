package com.devshiv.paypaltestjava.api.Customer;

public class RequestCustomerModel {
    private String uid;
    private String email;
    private String name;
    private String phone;

    public RequestCustomerModel(String uid, String email, String name, String phone) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public String get_id() {
        return uid;
    }

    public void set_id(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RequestCustomerModel{" +
                "_id='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
