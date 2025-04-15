package com.anirudhgv.stockease.data.network;

import com.anirudhgv.stockease.data.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("orders/")
    Call<List<Order>> getOrders();
}
