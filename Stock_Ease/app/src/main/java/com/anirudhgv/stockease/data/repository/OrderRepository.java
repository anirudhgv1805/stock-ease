package com.anirudhgv.stockease.data.repository;

import android.content.Context;
import android.os.Build;

import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.util.List;

import retrofit2.Callback;

public class OrderRepository {
    private final ApiService apiService;

    public OrderRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    public void getOrders(Callback<List<Order>> callback) {
        apiService.getOrders().enqueue(callback);
    }

    public void createOrder(String item, int quantity, String clientName, Callback<Void> callback) {
        Order order = new Order(item, quantity, clientName);
        apiService.postOrder(order).enqueue(callback);
    }

}
