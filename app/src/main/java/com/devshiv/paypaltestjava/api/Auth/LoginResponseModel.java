package com.devshiv.paypaltestjava.api.Auth;

public class LoginResponseModel {
    private Login data;

    public LoginResponseModel(Login data) {
        this.data = data;
    }

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
        this.data = data;
    }
}
