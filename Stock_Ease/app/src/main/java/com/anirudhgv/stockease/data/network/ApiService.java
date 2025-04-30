package com.anirudhgv.stockease.data.network;

import com.anirudhgv.stockease.data.model.AuthResponse;
import com.anirudhgv.stockease.data.model.Inventory;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.model.User;
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

    @GET("users")
    Call<List<User>> getAllUsers();

    @POST("users/owner/{id}/block")
    Call<Void> blockUser(@Path("id") Long userId);

    @POST("users/owner/{id}/unblock")
    Call<Void> unblockUser(@Path("id") Long userId);

    @GET("inventory")
    Call<List<Inventory>> getInventory();
}
