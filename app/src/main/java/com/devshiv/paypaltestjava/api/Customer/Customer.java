package com.devshiv.paypaltestjava.api.Customer;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("_id")
    private String id;
    @SerializedName("uid")
    private String uid;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("__v")
    private int __v;

    public Customer(String uid, String email, String name, String phone) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public Customer(String id, String uid, String email, String name, String phone, int __v) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.__v = __v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", __v=" + __v +
                '}';
    }
}
