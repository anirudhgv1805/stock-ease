package com.anirudhgv.stockease.data.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.dto.OwnerDashboardData;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerDashboardRepository {

    private final ApiService apiService;

    public OwnerDashboardRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    public LiveData<OwnerDashboardData> getOwnerDashboardData() {
        MutableLiveData<OwnerDashboardData> dashboardData = new MutableLiveData<>();

        apiService.getOwnerDashboardData().enqueue(new Callback<OwnerDashboardData>() {
            @Override
            public void onResponse(@NonNull Call<OwnerDashboardData> call, @NonNull Response<OwnerDashboardData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dashboardData.setValue(response.body());
                } else {
                    dashboardData.setValue(null); // optionally handle error state
                }
            }

            @Override
            public void onFailure(@NonNull Call<OwnerDashboardData> call, @NonNull Throwable t) {
                dashboardData.setValue(null); // optionally handle error
            }
        });

        return dashboardData;
    }

}
