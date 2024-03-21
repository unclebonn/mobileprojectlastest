package com.devshiv.paypaltestjava.api.Product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiServiceProduct {
    Gson gson = new GsonBuilder().setDateFormat("dd--MM-yyy").create();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    ApiServiceProduct API_SERVICE_PRODUCT = new Retrofit.Builder()

            .baseUrl("http://192.168.88.140:3000/")

            .addConverterFactory((GsonConverterFactory.create(gson)))
            .client(httpClient.build())
            .build()
            .create(ApiServiceProduct.class);


    // lay list san pham de tren trang dashboard
    @GET("api/product")
    Call<ProductResponseModel> getListProduct();



}
