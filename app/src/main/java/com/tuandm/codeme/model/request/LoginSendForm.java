package com.tuandm.codeme.model.request;

import com.google.gson.annotations.SerializedName;

public class LoginSendForm {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LoginSendForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
