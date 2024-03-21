package com.devshiv.paypaltestjava.api.Transaction;

public class TransactionRequestModel {
    private String cart;
    private double totalAmoount;

    public TransactionRequestModel(String cart, double totalAmoount) {
        this.cart = cart;
        this.totalAmoount = totalAmoount;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public double getTotalAmoount() {
        return totalAmoount;
    }

    public void setTotalAmoount(double totalAmoount) {
        this.totalAmoount = totalAmoount;
    }

    @Override
    public String toString() {
        return "TransactionRequestModel{" +
                "cart='" + cart + '\'' +
                ", totalAmoount=" + totalAmoount +
                '}';
    }
}

