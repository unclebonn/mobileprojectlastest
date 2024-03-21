package com.devshiv.paypaltestjava.api.Cart;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Cart {
    @SerializedName("customer")
    private String customer;
    @SerializedName("createAt")
    private Date createAt;
    @SerializedName("_id")

    private String _id;
    @SerializedName("__v")

    private int __v;


    public Cart(String customer){
        this.customer = customer;
    }
    public Cart(String _id, String customer, Date createAt, int __v) {
        this._id = _id;
        this.customer = customer;
        this.createAt = createAt;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "_id='" + _id + '\'' +
                ", customer=" + customer +
                ", createAt=" + createAt +
                ", __v=" + __v +
                '}';
    }
}
