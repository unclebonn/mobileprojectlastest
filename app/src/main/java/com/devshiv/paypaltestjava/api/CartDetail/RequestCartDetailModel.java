package com.devshiv.paypaltestjava.api.CartDetail;

import com.google.gson.annotations.SerializedName;

public class RequestCartDetailModel {
    @SerializedName("product")
    private String productId;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("cart")
    private String cartId;

    public RequestCartDetailModel(String productId, int quantity, String cartId) {
        this.productId = productId;
        this.quantity = quantity;
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "RequestCartDetailModel{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", cartId='" + cartId + '\'' +
                '}';
    }
}
