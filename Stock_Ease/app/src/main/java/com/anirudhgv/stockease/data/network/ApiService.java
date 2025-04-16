package com.anirudhgv.stockease.data.network;

import com.anirudhgv.stockease.data.model.LoginRequest;
import com.anirudhgv.stockease.data.model.LoginResponse;
import com.anirudhgv.stockease.data.model.Order;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // ApiService.java
    @GET("test")
    Call<ResponseBody> testApi();

    @GET("orders")
    Call<List<Order>> getOrders();

    @POST("login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("orders/add")
    Call<ResponseBody> postOrder(@Body Order order);

}
