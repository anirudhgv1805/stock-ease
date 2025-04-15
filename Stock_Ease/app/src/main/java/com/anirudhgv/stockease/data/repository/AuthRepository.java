package com.anirudhgv.stockease.data.repository;

import com.anirudhgv.stockease.data.model.LoginRequest;
import com.anirudhgv.stockease.data.model.LoginResponse;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Callback;

public class AuthRepository {

    private final ApiService apiService;

    public AuthRepository(SessionManager sessionManager) {
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    public void login(String username, String password, Callback<LoginResponse> callback) {
        LoginRequest request = new LoginRequest(username, password);
        apiService.login(request).enqueue(callback);
    }
}
