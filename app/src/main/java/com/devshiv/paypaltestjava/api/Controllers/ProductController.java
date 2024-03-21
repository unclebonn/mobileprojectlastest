package com.devshiv.paypaltestjava.api.Controllers;

import android.util.Log;

import com.devshiv.paypaltestjava.api.Product.ApiServiceProduct;
import com.devshiv.paypaltestjava.api.Product.Product;
import com.devshiv.paypaltestjava.api.Product.ProductResponseModel;
import com.devshiv.paypaltestjava.api.ProductDetail.ApiServiceProductDetail;
import com.devshiv.paypaltestjava.api.ProductDetail.ProductDetailResponseModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController {

    public interface ProductsCallBack {
        void onGetProductSuccess(List<Product> listProducts);

        void onGetProductFailed();
    }

    public interface ProductDetailCallBack {
        void onGetProductSuccess(Product product);

        void onGetProductFailed();
    }


    // api này đúng để lấy tất cả các sản phẩm hiện ra giao diện
    public void callApiGetProducts(ProductsCallBack productsCallBack) {
        ApiServiceProduct.API_SERVICE_PRODUCT.getListProduct().enqueue(new Callback<ProductResponseModel>() {
            @Override
            public void onResponse(Call<ProductResponseModel> call, Response<ProductResponseModel> response) {
                if (response.isSuccessful()) {
                    ProductResponseModel products = response.body();
                    List<Product> listProducts = products.getData();
                    productsCallBack.onGetProductSuccess(listProducts);

                    for (Product product : listProducts) {
                        String data = product.toString();
                        Log.d("hsagsadfsadfsa", "onResponse: " + data);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponseModel> call, Throwable t) {
                Log.d("fdsfasfdsafs", "onFailure: " + t.getMessage());
            }
        });
    }


    // api nay dung de lay detail san pham
    public void callApiGetProductDetail(String productId, ProductDetailCallBack productDetailCallBack) {
        ApiServiceProductDetail.API_SERVICE_PRODUCT.getProductDetail(productId).enqueue(new Callback<ProductDetailResponseModel>() {
            @Override
            public void onResponse(Call<ProductDetailResponseModel> call, Response<ProductDetailResponseModel> response) {
                if (response.isSuccessful()) {
                    ProductDetailResponseModel data = response.body();
                    Product product = data.getData();
                    productDetailCallBack.onGetProductSuccess(product);
                    Log.d("productDetails", "onResponse: " + product.toString());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponseModel> call, Throwable t) {

            }
        });
    }
}
