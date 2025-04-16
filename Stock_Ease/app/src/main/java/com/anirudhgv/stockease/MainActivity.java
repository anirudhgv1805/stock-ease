package com.anirudhgv.stockease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.activity.AddOrderActivity;
import com.anirudhgv.stockease.ui.activity.LoginActivity;
import com.anirudhgv.stockease.ui.activity.TransactionActivity;
import com.anirudhgv.stockease.ui.adapter.OrderAdapter;
import com.anirudhgv.stockease.ui.viewmodel.OrderViewModel;
import com.anirudhgv.stockease.ui.activity.EditOrderActivity;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private OrderAdapter orderAdapter;
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.fetchAuthToken() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Setup buttons
        MaterialButton btnViewOrders = findViewById(R.id.btnViewOrders);
        MaterialButton btnAddOrder = findViewById(R.id.btnAddOrder);
        MaterialButton btnTransactions = findViewById(R.id.btnTransactions);
        MaterialButton btnEditOrder = findViewById(R.id.btnEditOrder);
        RecyclerView recyclerOrders = findViewById(R.id.recyclerOrders);

        // RecyclerView & ViewModel setup
        orderAdapter = new OrderAdapter();
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrders.setAdapter(orderAdapter);

        orderViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(OrderViewModel.class);

        orderViewModel.getOrderList().observe(this, orders -> {
            orderAdapter.setOrders(orders);
        });

        btnViewOrders.setOnClickListener(v -> orderViewModel.fetchOrders());

        btnAddOrder.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddOrderActivity.class);
            startActivity(intent);
        });

        btnTransactions.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
            startActivity(intent);
        });

        btnEditOrder.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditOrderActivity.class);
            startActivity(intent);
        });

        // Test backend connection
        ApiService apiService = ApiClient.getApiService(sessionManager);
        apiService.testApi().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() == null) throw new AssertionError();
                        String responseBody = response.body().string();
                        Toast.makeText(MainActivity.this, "✅ Backend connected: " + responseBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Log.e("API Test", "IOException: " + e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "❌ Backend error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
