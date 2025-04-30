package com.anirudhgv.stockease.data.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private final ApiService apiService;

    public ProductRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    public LiveData<Boolean> createProduct(Product product) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        apiService.createProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                result.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                result.setValue(false);
            }
        });

        return result;
    }
}
