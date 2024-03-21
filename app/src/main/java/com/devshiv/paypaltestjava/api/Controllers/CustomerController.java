package com.devshiv.paypaltestjava.api.Controllers;

import com.devshiv.paypaltestjava.api.Customer.ApiServiceCustomer;
import com.devshiv.paypaltestjava.api.Customer.Customer;
import com.devshiv.paypaltestjava.api.Customer.RequestCustomerModel;
import com.devshiv.paypaltestjava.api.Customer.ResponseCustomerModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerController {

    public interface CustomerCallBack {
        void onCreateCustomerSuccess (Customer customer);
        void onCreateCustomerFailed (String msgFailed);
    }



    // api để tạo mới customer
    public void callApiCreateNewCustomer(RequestCustomerModel requestCustomerModel, CustomerCallBack callBack){
        ApiServiceCustomer.API_SERVICE_CUSTOMER.createNewCustomer(requestCustomerModel).enqueue(new Callback<ResponseCustomerModel>() {
            @Override
            public void onResponse(Call<ResponseCustomerModel> call, Response<ResponseCustomerModel> response) {
                if(response.isSuccessful()){
                    ResponseCustomerModel responseCustomerModel = response.body();
                    Customer customer = responseCustomerModel.getData();
                    if(customer != null){
                        callBack.onCreateCustomerSuccess(customer);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomerModel> call, Throwable t) {

            }
        });
    }


    //api này dùng để lấy thông tin khách hàng
    public void callApiGetCustomer(String token,String uid, CustomerCallBack customerCallBack){
        ApiServiceCustomer.API_SERVICE_CUSTOMER.getCustomer(token,uid).enqueue(new Callback<ResponseCustomerModel>() {
            @Override
            public void onResponse(Call<ResponseCustomerModel> call, Response<ResponseCustomerModel> response) {
                if(response.isSuccessful()){
                    ResponseCustomerModel responseCustomerModel = response.body();
                    Customer customer = responseCustomerModel.getData();
                    if(customer != null ){
                        customerCallBack.onCreateCustomerSuccess(customer);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomerModel> call, Throwable t) {

            }
        });
    }
}
