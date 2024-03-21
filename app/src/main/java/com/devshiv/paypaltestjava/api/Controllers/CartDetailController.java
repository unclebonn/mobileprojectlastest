package com.devshiv.paypaltestjava.api.Controllers;

import android.util.Log;

import com.devshiv.paypaltestjava.api.CartDetail.ApiServiceCartDetail;
import com.devshiv.paypaltestjava.api.CartDetail.CartDetail;
import com.devshiv.paypaltestjava.api.CartDetail.CartDetailCreate;
import com.devshiv.paypaltestjava.api.CartDetail.RequestCartDetailModel;
import com.devshiv.paypaltestjava.api.CartDetail.ResponseCartDetailModel;
import com.devshiv.paypaltestjava.api.CartDetail.ResponseCreateCartDetaiModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDetailController {

    public interface CartDetailCallBack {
        void onGetCartDetailSuccess(List<CartDetail> cartDetail);

        void onGetCartDetailFailed(String msgFailed);
    }


    public interface CreateCartDetailCallBack {
        void onCreateCartDetailSuccess(CartDetailCreate cartDetailCreate);

        void onGetCartDetailFailed(String msgFailed);
    }


    // api này dùng để lấy chi tiết sản phẩm trong giỏ hàng
    public void callApiGetCartDetailById(String token, String id, CartDetailCallBack cartDetailCallBack) {
        ApiServiceCartDetail.API_SERVICE_CART_DETAIL.getCartDetailOfCustomer(token, id).enqueue(new Callback<ResponseCartDetailModel>() {
            @Override
            public void onResponse(Call<ResponseCartDetailModel> call, Response<ResponseCartDetailModel> response) {
                if (response.isSuccessful()) {
                    ResponseCartDetailModel cartDetailModel = response.body();
                    List<CartDetail> cartDetail = cartDetailModel.getData();
                    cartDetailCallBack.onGetCartDetailSuccess(cartDetail);
                }
            }

            @Override
            public void onFailure(Call<ResponseCartDetailModel> call, Throwable t) {
                Log.d("lasycartdetail", "onFailure: " + t.getMessage());
            }
        });
    }



    // api này dùng để thêm sản phẩm vào giỏ hàng
    public void callApiCreateCartDetail(String token, RequestCartDetailModel requestCartDetailModel, CreateCartDetailCallBack createCartDetailCallBack) {
        ApiServiceCartDetail.API_SERVICE_CART_DETAIL.createCartDetail(token, requestCartDetailModel).enqueue(new Callback<ResponseCreateCartDetaiModel>() {
            @Override
            public void onResponse(Call<ResponseCreateCartDetaiModel> call, Response<ResponseCreateCartDetaiModel> response) {
                if (response.isSuccessful()) {
                    ResponseCreateCartDetaiModel responseCreateCartDetaiModel = response.body();
                    CartDetailCreate cartDetail = responseCreateCartDetaiModel.getData();

                    createCartDetailCallBack.onCreateCartDetailSuccess(cartDetail);


                }
            }

            @Override
            public void onFailure(Call<ResponseCreateCartDetaiModel> call, Throwable t) {
                Log.d("Fdsfasf", "onFailure: " + t.getMessage());
            }
        });
    }
}
