package com.devshiv.paypaltestjava.api.ProductDetail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServiceProductDetail {
    Gson gson = new GsonBuilder().setDateFormat("dd--MM-yyy").create();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    ApiServiceProductDetail API_SERVICE_PRODUCT = new Retrofit.Builder()
            .baseUrl("http://192.168.88.140:3000/")
            .addConverterFactory((GsonConverterFactory.create(gson)))
            .client(httpClient.build())
            .build()
            .create(ApiServiceProductDetail.class);

    // api của product detail
    @GET("api/product/{productID}")
    Call<ProductDetailResponseModel> getProductDetail(@Path("productID") String productID);
}
