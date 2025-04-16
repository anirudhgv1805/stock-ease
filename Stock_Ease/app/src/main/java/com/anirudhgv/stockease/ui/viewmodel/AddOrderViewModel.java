package com.anirudhgv.stockease.ui.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anirudhgv.stockease.data.repository.AuthRepository;
import com.anirudhgv.stockease.data.repository.OrderRepository;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOrderViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> orderAdded = new MutableLiveData<>();

    private final OrderRepository orderRepository;
    private final SessionManager sessionManager;

    public AddOrderViewModel(@NonNull Application application){
        super(application);
        this.sessionManager = new SessionManager(application.getApplicationContext());
        this.orderRepository = new OrderRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> getOrderAdded() {
        return orderAdded;
    }

    public void submitOrder(String item, int quantity, String clientName) {
        orderRepository.createOrder(item, quantity, clientName, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                orderAdded.postValue(response.isSuccessful());
                Toast.makeText(getApplication(),"Order Added Successfully",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                orderAdded.postValue(false);
            }
        });
    }
}
