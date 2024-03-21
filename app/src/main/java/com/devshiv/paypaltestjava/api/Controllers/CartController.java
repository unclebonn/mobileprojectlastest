package com.devshiv.paypaltestjava.api.Controllers;

import android.util.Log;

import com.devshiv.paypaltestjava.api.Cart.ApiServiceCart;
import com.devshiv.paypaltestjava.api.Cart.Cart;
import com.devshiv.paypaltestjava.api.Cart.RequestCartModel;
import com.devshiv.paypaltestjava.api.Cart.ResponseCartModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartController {

    public interface CartDetailCallBack {
        void onSuccessGetCart(Cart cart);

        void onFailedGetCart(String msgFailed);
    }

    public interface CartDetailCreateCallBack {
        void onSuccessCreateCart(Cart cart);

        void onFailedCreateCart(String msgFailed);
    }


    // api dùng để lấy id giỏ hàng của người dùng
    public void callApiGetCart(String token, String customerId, CartDetailCallBack cartDetailCallBack) {
        ApiServiceCart.API_SERVICE_CART.getCart(token, customerId).enqueue(new Callback<ResponseCartModel>() {
            @Override
            public void onResponse(Call<ResponseCartModel> call, Response<ResponseCartModel> response) {
                if(response.isSuccessful()){
                    ResponseCartModel cartModel = response.body();
                    Cart cart = cartModel.getData();
                    if(cart != null){
                        cartDetailCallBack.onSuccessGetCart(cart);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCartModel> call, Throwable t) {

            }
        });
    }


    public void callApiCreateCart (RequestCartModel requestCartModel, CartDetailCreateCallBack callBack ){
        ApiServiceCart.API_SERVICE_CART.createCart(requestCartModel).enqueue(new Callback<ResponseCartModel>() {
            @Override
            public void onResponse(Call<ResponseCartModel> call, Response<ResponseCartModel> response) {
//                if(response.isSuccessful()){
                    ResponseCartModel responseCartModel = response.body();
                    Cart cart = responseCartModel.getData();
                    callBack.onSuccessCreateCart(cart);
//                }
            }

            @Override
            public void onFailure(Call<ResponseCartModel> call, Throwable t) {
                Log.d("failedd", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
