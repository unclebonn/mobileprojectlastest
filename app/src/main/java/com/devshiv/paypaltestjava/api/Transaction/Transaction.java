package com.devshiv.paypaltestjava.api.Transaction;

import com.devshiv.paypaltestjava.api.Cart.Cart;

import java.util.Date;

public class Transaction {
    private String _id;
    private Date createAt;

    private Cart cart;
    private double totalAmount;

    private int __v;


    public Transaction(String _id, Date createAt, Cart cart, double totalAmount, int __v) {
        this._id = _id;
        this.createAt = createAt;

        this.cart = cart;
        this.totalAmount = totalAmount;
        this.__v = __v;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "_id='" + _id + '\'' +
                ", createAt=" + createAt +
                ", cart='" + cart + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}


