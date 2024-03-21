package com.devshiv.paypaltestjava.api.Customer;

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

public interface ApiServiceCustomer {
    Gson gson = new GsonBuilder().setDateFormat("dd--MM-yyy").create();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    ApiServiceCustomer API_SERVICE_CUSTOMER = new Retrofit.Builder()

            .baseUrl("http://192.168.88.140:3000/")


            .addConverterFactory((GsonConverterFactory.create(gson)))
            .client(httpClient.build())
            .build()
            .create(ApiServiceCustomer.class);


    @POST("api/customer/create")
    Call<ResponseCustomerModel> createNewCustomer(@Body RequestCustomerModel requestCustomerModel);

    @GET("api/customer/{customerId}")
    Call<ResponseCustomerModel> getCustomer(@Header("x-access-token")String token, @Path("customerId") String uid);
}
