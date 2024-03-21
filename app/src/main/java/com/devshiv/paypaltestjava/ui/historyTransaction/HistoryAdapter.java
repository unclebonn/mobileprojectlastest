package com.devshiv.paypaltestjava.ui.historyTransaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.Transaction.Transaction;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Transaction> transactionList;

    public HistoryAdapter(Context context, int layout, List<Transaction> transactionList) {
        this.context = context;
        this.layout = layout;
        this.transactionList = transactionList;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView transactionId = view.findViewById(R.id.idTransaction);
        TextView dateTransaction = view.findViewById(R.id.dateTransaction);
        TextView totalAmount = view.findViewById(R.id.totalAmountTransaction);

        Transaction transaction = transactionList.get(i);
        transactionId.setText("ID: " + transaction.get_id());
        dateTransaction.setText("Date: " + String.valueOf(transaction.getCreateAt().toLocaleString()));
        totalAmount.setText("Total Amount: " + String.valueOf(transaction.getTotalAmount() +"d"));

        return view;
    }
}
