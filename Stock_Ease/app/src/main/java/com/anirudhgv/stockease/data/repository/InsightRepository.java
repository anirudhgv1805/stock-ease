package com.anirudhgv.stockease.data.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.MainActivity;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsightRepository {
    private final ApiService apiService;

    private InsightRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    public void getNoOrders(Callback<Number> callback) {
        apiService.getNoOrders().enqueue(callback);
    }

}
