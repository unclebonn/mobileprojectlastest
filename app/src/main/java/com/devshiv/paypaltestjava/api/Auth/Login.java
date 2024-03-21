package com.devshiv.paypaltestjava.api.Auth;

import com.devshiv.paypaltestjava.api.Cart.Cart;
import com.devshiv.paypaltestjava.api.Customer.Customer;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("customer")
    private Customer customer;

    @SerializedName("cart")
    private Cart cart;
    @SerializedName("success")
    Boolean success;
    @SerializedName("token")
    String token;
    @SerializedName("message")
    String message;

    public Login(Customer customer, Cart cart, Boolean success, String token, String message) {
        this.customer = customer;
        this.cart = cart;
        this.success = success;
        this.token = token;
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Login{" +
                "customer=" + customer +
                ", cart=" + cart +
                ", success=" + success +
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
