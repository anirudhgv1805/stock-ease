package com.anirudhgv.stockease.ui.owner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anirudhgv.stockease.data.model.dto.OwnerDashboardData;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerViewModel extends ViewModel {


    public void getDashboardData(SessionManager sessionManager) {
        ApiService apiService = ApiClient.getApiService(sessionManager);

        apiService.fetchDashboardData().enqueue(new Callback<OwnerDashboardData>() {
            @Override
            public void onResponse(Call<OwnerDashboardData> call, Response<OwnerDashboardData> response) {
                if(response.isSuccessful() && response.body()!=null){
                    OwnerDashboardData ownerDashboardData = response.body();

                }
            }

            @Override
            public void onFailure(Call<OwnerDashboardData> call, Throwable t) {

            }
        });
    }

}
