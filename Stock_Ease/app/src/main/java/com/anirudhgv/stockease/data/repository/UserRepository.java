package com.anirudhgv.stockease.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.User;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    public LiveData<List<User>> getAllUsers() {
        MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
        apiService.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call,@NonNull Response<List<User>> response) {
                Log.d("UserRepository", "Response Code: " + response.code());
                if (response.isSuccessful()) {
                    usersLiveData.setValue(response.body());
                } else {
                    usersLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call,@NonNull Throwable t) {
                usersLiveData.setValue(null);
            }
        });
        System.out.println(usersLiveData);
        return usersLiveData;
    }

    public void blockUser(Long userId, Runnable onSuccess, Runnable onFailure) {
        apiService.blockUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,@NonNull Response<Void> response) {
                if (response.isSuccessful()) onSuccess.run();
                else onFailure.run();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                onFailure.run();
            }
        });
    }

    public void unblockUser(Long userId, Runnable onSuccess, Runnable onFailure) {
        apiService.unblockUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,@NonNull Response<Void> response) {
                if (response.isSuccessful()) onSuccess.run();
                else onFailure.run();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                onFailure.run();
            }
        });
    }
}
