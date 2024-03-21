package com.devshiv.paypaltestjava.ui.historyTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.Controllers.TransactionController;
import com.devshiv.paypaltestjava.api.Transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HistoryTransaction extends AppCompatActivity {

    ListView transactionListView;
    HistoryAdapter historyAdapter;

    ArrayList<Transaction> transactionArrayList;
    String token, customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_transaction);


        transactionListView = (ListView) findViewById(R.id.historyTransactionlv);
        transactionArrayList = new ArrayList<>();
        historyAdapter = new HistoryAdapter(this,R.layout.history_transaction_item,transactionArrayList);
        transactionListView.setAdapter(historyAdapter);


        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        customerId = sharedPreferences.getString("customerId", "");


        TransactionController transactionController = new TransactionController();
        transactionController.callApiHistoryTransaction(token, customerId, new TransactionController.HistoryTransactionCallBack() {
            @Override
            public void onTransacSuccess(List<Transaction> transactions) {
                for (Transaction transaction : transactions) {
                    transactionArrayList.add(transaction);
                }

                historyAdapter.notifyDataSetChanged();
            }
        });
    }
}