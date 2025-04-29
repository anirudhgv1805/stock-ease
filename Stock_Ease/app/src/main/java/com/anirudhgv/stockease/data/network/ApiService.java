package com.anirudhgv.stockease.data.network;

import com.anirudhgv.stockease.data.model.AuthResponse;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.model.dto.OwnerDashboardData;
import com.anirudhgv.stockease.data.model.dto.UserDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("test")
    Call<ResponseBody> testApi();

    @POST("auth/register")
    Call<AuthResponse> register(@Body UserDto user);

    @POST("auth/login")
    Call<AuthResponse> login(@Body UserDto user);

//    for owner dashboard
    @GET("owner/dashboardData")
    Call<OwnerDashboardData> getOwnerDashboardData();

    @POST("products")
    Call<Void> createProduct(@Body Product product);



}
