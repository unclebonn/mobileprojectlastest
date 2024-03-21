package com.devshiv.paypaltestjava.api.ProductDetail;

import com.devshiv.paypaltestjava.api.Product.Product;


public class ProductDetailResponseModel {
    private Product data;

    public Product getData() {
        return data;
    }

    public void setData(Product data) {
        this.data = data;
    }
}
