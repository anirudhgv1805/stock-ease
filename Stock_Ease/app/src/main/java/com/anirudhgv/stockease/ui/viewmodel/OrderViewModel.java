package com.anirudhgv.stockease.ui.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.repository.OrderRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Order>> orderList = new MutableLiveData<>();
    private final OrderRepository orderRepository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application.getApplicationContext());
    }

    public LiveData<List<Order>> getOrderList() {
        return orderList;
    }

    public void fetchOrders() {
        orderRepository.getOrders(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    orderList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Log.e("data cannot be fetched","data from backend cannot be retrieved",t);
                Toast.makeText(getApplication(), "The data cannot be fetched", Toast.LENGTH_LONG).show();
            }
        });
    }
}
