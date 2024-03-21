package com.devshiv.paypaltestjava.api.Controllers;

import android.util.Log;

import com.devshiv.paypaltestjava.api.Auth.ApiServiceLogin;
import com.devshiv.paypaltestjava.api.Auth.Login;
import com.devshiv.paypaltestjava.api.Auth.LoginRequestModel;
import com.devshiv.paypaltestjava.api.Auth.LoginResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {

    public interface LoginCallBack {
        void onLoginSuccess(Login login );
    }


    //api này dùng để đăng nhập, chỗ này truyền uid trên firebase xuống nha
    public void callApiLogin(String uid, LoginCallBack callBack) {
        LoginRequestModel requestBody = new LoginRequestModel(uid);
        ApiServiceLogin.API_SERVICE_LOGIN.login(requestBody).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    LoginResponseModel data = response.body();
                    Login loginData = data.getData();
                    Log.d("loginDatat", "onResponse: " + loginData.toString());
                    if (loginData != null) {
                        callBack.onLoginSuccess(loginData);
                    }


                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d("failed", "onFailure: " + t.getMessage());
            }
        });
    }
}
