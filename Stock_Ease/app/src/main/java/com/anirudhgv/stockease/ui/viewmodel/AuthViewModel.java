package com.anirudhgv.stockease.ui.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.LoginResponse;
import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.repository.AuthRepository;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.util.List;
import com.anirudhgv.stockease.data.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<Boolean> loginResult = new MutableLiveData<>();
    private final SessionManager sessionManager;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        sessionManager = new SessionManager(application.getApplicationContext());
        authRepository = new AuthRepository(sessionManager);
    }

    public LiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        authRepository.login(username, password, new Callback<LoginResponse>() {

            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sessionManager.saveAuthToken(response.body().getToken());
                    loginResult.postValue(true);
                } else {
                    loginResult.postValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.e("data cannot be fetched","data from backend cannot be retrieved",t);
                Toast.makeText(getApplication(), "The data cannot be fetched", Toast.LENGTH_LONG).show();
            }
        });
    }
}
