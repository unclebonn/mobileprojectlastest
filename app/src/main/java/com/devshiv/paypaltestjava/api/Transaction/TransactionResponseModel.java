package com.devshiv.paypaltestjava.api.Transaction;

import java.util.List;

public class TransactionResponseModel {
    private List<Transaction> data;

    public List<Transaction> getData() {
        return data;
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }
}
