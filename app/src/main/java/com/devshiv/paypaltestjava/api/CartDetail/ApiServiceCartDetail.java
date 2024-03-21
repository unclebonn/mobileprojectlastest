package com.devshiv.paypaltestjava.api.CartDetail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceCartDetail {

    Gson gson = new GsonBuilder().setDateFormat("dd--MM-yyy").create();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    ApiServiceCartDetail API_SERVICE_CART_DETAIL = new Retrofit.Builder()

            .baseUrl("http://192.168.88.140:3000/")

            .addConverterFactory((GsonConverterFactory.create(gson)))
            .client(httpClient.build())
            .build()
            .create(ApiServiceCartDetail.class);


    @GET("api/cartdetail/detail/{id}")
    Call<ResponseCartDetailModel> getCartDetailOfCustomer(@Header("x-access-token") String token, @Path("id") String customerId);

    @POST("api/cartdetail/create")
    Call<ResponseCreateCartDetaiModel> createCartDetail(@Header("x-access-token") String token, @Body RequestCartDetailModel requestCartDetailModel);

}
