package com.devshiv.paypaltestjava.api.Customer;

import com.google.gson.annotations.SerializedName;

public class ResponseCustomerModel {

    @SerializedName("data")
    private Customer data;
    @SerializedName("message")

    private String message;

    public ResponseCustomerModel(Customer data, String message) {
        this.data = data;
        this.message = message;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseCustomerModel{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
