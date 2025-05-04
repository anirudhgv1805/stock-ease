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
    private MutableLiveData<OwnerDashboardData> dashboardDataLiveData;

    public OwnerDashboardRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
        this.dashboardDataLiveData = new MutableLiveData<>();
    }

    // This will be the single source of data for the UI
    public LiveData<OwnerDashboardData> getOwnerDashboardData() {
        return dashboardDataLiveData;
    }

    public void refreshOwnerDashboardData() {
        apiService.getOwnerDashboardData().enqueue(new Callback<OwnerDashboardData>() {
            @Override
            public void onResponse(@NonNull Call<OwnerDashboardData> call, @NonNull Response<OwnerDashboardData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dashboardDataLiveData.setValue(response.body());
                } else {
                    dashboardDataLiveData.setValue(null); // Optionally handle error state
                }
            }

            @Override
            public void onFailure(@NonNull Call<OwnerDashboardData> call, @NonNull Throwable t) {
                dashboardDataLiveData.setValue(null); // Optionally handle error
            }
        });
    }
}
