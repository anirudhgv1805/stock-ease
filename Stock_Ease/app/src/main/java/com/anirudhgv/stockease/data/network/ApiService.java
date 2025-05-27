package com.anirudhgv.stockease.data.network;

import com.anirudhgv.stockease.data.model.AuthResponse;
import com.anirudhgv.stockease.data.model.Inventory;
import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.model.OrderItem;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.model.User;
import com.anirudhgv.stockease.data.model.dto.OwnerDashboardData;
import com.anirudhgv.stockease.data.model.dto.StatusUpdateDto;
import com.anirudhgv.stockease.data.model.dto.UserDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<Product> createProduct(@Body Product product);

    @GET("users")
    Call<List<User>> getAllUsers();

    @POST("users/owner/{id}/block")
    Call<Void> blockUser(@Path("id") Long userId);

    @POST("users/owner/{id}/unblock")
    Call<Void> unblockUser(@Path("id") Long userId);

    @GET("inventory")
    Call<List<Inventory>> getInventory();

    @PUT("products/{id}")
    Call<Product> updateProduct(@Path("id") long id, @Body Product product);

    @GET("products")
    Call<List<Product>> getAllProducts();

    @POST("inventory/update/{productId}")
    Call<Inventory> updateInventory(
            @Path("productId") Long productId,
            @Query("quantityChange") int quantityChange,
            @Query("reason") String reason,
            @Query("userId") Long userId
    );

    @PUT("/api/orders/{orderId}/status")
    Call<Order> updateOrderStatus(@Path("orderId") Long orderId, @Body StatusUpdateDto statusDto);

    @GET("orders")
    Call<List<Order>> getAllOrders();

    @POST("orders")
    Call<Order> createOrder(@Body Order order);

    @POST("orders")
    Call<Order> placeOrder(@Body Order order);

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") Long productId);

    @PUT("products/{id}")
    Call<Product> updateProduct(@Path("id") Long productId, @Body Product product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") Long productId);


}
