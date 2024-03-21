package com.devshiv.paypaltestjava.api.Controllers;

import android.util.Log;
import android.widget.Toast;

import com.devshiv.paypaltestjava.api.Auth.Login;
import com.devshiv.paypaltestjava.api.Transaction.ApiServiceTransaction;
import com.devshiv.paypaltestjava.api.Transaction.Transaction;
import com.devshiv.paypaltestjava.api.Transaction.TransactionRequestModel;
import com.devshiv.paypaltestjava.api.Transaction.TransactionResponseModel;
import com.devshiv.paypaltestjava.ui.cartDetail.CartDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionController {

    public interface TransactionCallBack {
        void onTransacSuccess(String message);
    }

    public interface HistoryTransactionCallBack {
        void onTransacSuccess(List<Transaction> transactions);
    }

    public void callApiTransaction(String token, TransactionRequestModel transactionRequestModel, TransactionCallBack transactionCallBack) {
        ApiServiceTransaction.API_TRANSACTION_PRODUCT.BoughtItems(token, transactionRequestModel).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    transactionCallBack.onTransacSuccess("Thanks for buying our product");

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fdsfds", "onFailureTransaction: " + t.getMessage());
            }
        });
    }


    public void callApiHistoryTransaction(String token, String customerId, HistoryTransactionCallBack historyTransactionCallBack){
        ApiServiceTransaction.API_TRANSACTION_PRODUCT.getHistoryTransactions(token,customerId).enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                if(response.isSuccessful()){
                    TransactionResponseModel transactionResponseModel = response.body();
                    historyTransactionCallBack.onTransacSuccess(transactionResponseModel.getData());
                }
            }

            @Override
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                Log.d("historyTransactionFaile", "onFailure: " + t.getMessage());
            }
        });

    }

}
