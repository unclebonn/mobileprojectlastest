package com.devshiv.paypaltestjava.ui.cartDetail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.CartDetail.CartDetail;
import java.util.List;

public class CartDetailAdapter extends BaseAdapter {

    private CartDetailActivity context;
    private int layout;
    private List<CartDetail> cartDetailList;

    public CartDetailAdapter(CartDetailActivity context, int layout, List<CartDetail> cartDetailList) {
        this.context = context;
        this.layout = layout;
        this.cartDetailList = cartDetailList;
    }

    @Override
    public int getCount() {
        return cartDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);

        TextView tensanpham = convertView.findViewById(R.id.tvTitle1);
        TextView mieuta = convertView.findViewById(R.id.tvDes);
        TextView giatien = convertView.findViewById(R.id.tvPrice);


        CartDetail cartDetail = cartDetailList.get(position);
        Log.d("xemthuneconcho", "getView: " + cartDetail.toString());
//        tensanpham.setText("43214123");
//        mieuta.setText("4321412342134");
//        giatien.setText("11111111");
        tensanpham.setText(cartDetail.getProduct().getName());
        mieuta.setText(cartDetail.getProduct().getDescription());
        giatien.setText(String.valueOf(cartDetail.getProduct().getPrice()));

        return convertView;
    }
}
