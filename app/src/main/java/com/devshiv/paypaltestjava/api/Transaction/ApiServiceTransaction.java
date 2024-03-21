package com.devshiv.paypaltestjava.api.Transaction;

import com.devshiv.paypaltestjava.api.Product.ApiServiceProduct;

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

public interface ApiServiceTransaction {

    Gson gson = new GsonBuilder().setDateFormat("dd--MM-yyy").create();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    ApiServiceTransaction API_TRANSACTION_PRODUCT = new Retrofit.Builder()
            .baseUrl("http://192.168.88.140:3000/")
            .addConverterFactory((GsonConverterFactory.create(gson)))
            .client(httpClient.build())
            .build()
            .create(ApiServiceTransaction.class);


    @GET("api/transaction/history-transaction/{id}")
    Call<TransactionResponseModel> getHistoryTransactions(@Header("x-access-token") String token, @Path("id") String customerID);


    @POST("api/transaction/create")
    Call<String> BoughtItems(@Header("x-access-token") String token , @Body TransactionRequestModel TransactionRequestModel);
}
