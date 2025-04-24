package com.anirudhgv.stockease.ui.login;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anirudhgv.stockease.data.model.AuthResponse;
import com.anirudhgv.stockease.data.model.dto.UserDto;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void login(UserDto user, SessionManager sessionManager) {
        ApiService api = ApiClient.getApiService(sessionManager);
        api.login(user).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse auth = response.body();
                    sessionManager.saveAuthToken(auth.getAccessToken());
                    sessionManager.saveUserRole(auth.getRole().toString());
                    loginSuccess.setValue(true);
                } else {
                    errorMessage.setValue("Login failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
