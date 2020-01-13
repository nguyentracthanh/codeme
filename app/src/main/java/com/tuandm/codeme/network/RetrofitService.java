package com.tuandm.codeme.network;


import com.tuandm.codeme.model.request.CreateStatusSendForm;
import com.tuandm.codeme.model.request.LikeSendForm;
import com.tuandm.codeme.model.request.LoginSendForm;
import com.tuandm.codeme.model.request.RegisterSendForm;
import com.tuandm.codeme.model.response.Profile;
import com.tuandm.codeme.model.response.Status;
import com.tuandm.codeme.model.response.UserInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {

    @POST(API.LOGIN)
    Call<UserInfo> login(@Body LoginSendForm sendForm);

    @POST(API.REGISTER)
    Call<UserInfo> register(@Body RegisterSendForm sendForm);

    @GET(API.GET_ALL_POST)
    Call<ArrayList<Status>> getAllStatus(@Query("userId") String userId);

    @POST(API.LIKE_STATUS)
    Call<Void> likeStatus(@Body LikeSendForm sendForm);

    @POST(API.CREATE_STATUS)
    Call<Status> createStatus(@Body CreateStatusSendForm sendForm);

    @GET(API.GET_PROFILE)
    Call<Profile> getProfile(@Query("username") String user, @Header("userId") String userId);

}
