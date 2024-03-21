package com.devshiv.paypaltestjava.api.CartDetail;

import com.google.gson.annotations.SerializedName;

public class CartDetailCreate {
    @SerializedName("_id")
    private String _id;

    @SerializedName("status")
    private String status;
    @SerializedName("product")

    private String product;
    @SerializedName("quantity")

    private int quantity;
    @SerializedName("cart")

    private String cart;

    @SerializedName("createAt")
    private String createAt;
    @SerializedName("__v")

    private int __v;

    @Override
    public String toString() {
        return "CartDetailCreate{" +
                "_id='" + _id + '\'' +
                ", status='" + status + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", cart='" + cart + '\'' +
                ", createAt='" + createAt + '\'' +
                ", __v=" + __v +
                '}';
    }

    public CartDetailCreate(String _id, String status, String product, int quantity, String cart, String createAt, int __v) {
        this._id = _id;
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.createAt = createAt;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
