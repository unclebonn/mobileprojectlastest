package com.devshiv.paypaltestjava.api.Product;

import java.util.List;

public class ProductResponseModel {
    private List<Product> data;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
