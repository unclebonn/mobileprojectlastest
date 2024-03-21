package com.devshiv.paypaltestjava.api.Auth;

public class LoginRequestModel {
    private String uid;

    public LoginRequestModel(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "LoginRequestModel{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
