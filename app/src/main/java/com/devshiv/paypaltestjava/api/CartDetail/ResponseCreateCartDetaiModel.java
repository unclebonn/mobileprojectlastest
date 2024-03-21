package com.devshiv.paypaltestjava.api.CartDetail;

public class ResponseCreateCartDetaiModel {
    private CartDetailCreate data;

    public ResponseCreateCartDetaiModel(CartDetailCreate data) {
        this.data = data;
    }

    public CartDetailCreate getData() {
        return data;
    }

    public void setData(CartDetailCreate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseCreateCartDetaiModel{" +
                "data=" + data +
                '}';
    }
}


