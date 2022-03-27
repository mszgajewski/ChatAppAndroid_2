package com.mszgajewski.chatappandroid_2.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiService {

    @POST("send")
    Call<String> sendMeesage(
            @HeaderMap HashMap<String, String> headers,
            @Body String messageBody);
}
