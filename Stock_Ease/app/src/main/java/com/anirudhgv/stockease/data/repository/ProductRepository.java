package com.anirudhgv.stockease.data.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private final ApiService apiService;
    public ProductRepository(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        this.apiService = ApiClient.getApiService(sessionManager);
    }

    // Create Product
    public LiveData<Boolean> createProduct(Product product) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        apiService.createProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    result.setValue(true);
                } else {
                    result.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                result.setValue(false);
            }
        });

        return result;
    }

    // Get Product by ID
    public LiveData<Product> getProductById(Long productId) {
        MutableLiveData<Product> result = new MutableLiveData<>();

        apiService.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }
    public LiveData<List<Product>> getAllProducts() {
        MutableLiveData<List<Product>> result = new MutableLiveData<>();

        apiService.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    // Update Product
    public LiveData<Boolean> updateProduct(Long productId, Product product) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        apiService.updateProduct(productId, product).enqueue(new Callback<Product>() {
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

    // Delete Product
    public LiveData<Boolean> deleteProduct(Long productId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        apiService.deleteProduct(productId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                result.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                result.setValue(false);
            }
        });

        return result;
    }
}
