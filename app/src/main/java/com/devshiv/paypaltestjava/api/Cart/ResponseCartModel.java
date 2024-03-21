package com.devshiv.paypaltestjava.api.Cart;

import com.google.gson.annotations.SerializedName;

public class ResponseCartModel {

    @SerializedName("data")

    private Cart data;
    @SerializedName("message")
    private String message;

    public ResponseCartModel(String message, Cart data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Cart getData() {
        return data;
    }

    public void setData(Cart data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseCartModel{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
