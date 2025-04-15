package com.anirudhgv.stockease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.data.model.LoginRequest;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.activity.LoginActivity;
import com.anirudhgv.stockease.ui.adapter.OrderAdapter;
import com.anirudhgv.stockease.ui.viewmodel.OrderViewModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private OrderAdapter orderAdapter;
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.fetchAuthToken() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Button btnOrders = findViewById(R.id.btnOrders);
        RecyclerView recyclerOrders = findViewById(R.id.recyclerOrders);

        orderAdapter = new OrderAdapter();
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrders.setAdapter(orderAdapter);

        orderViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(OrderViewModel.class);

        btnOrders.setOnClickListener(v -> {
            orderViewModel.fetchOrders();
        });

        orderViewModel.getOrderList().observe(this, orders -> {
            orderAdapter.setOrders(orders);
        });

        ApiService apiService = ApiClient.getApiService(sessionManager);

        apiService.testApi().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() == null) throw new AssertionError();
                        String responseBody = response.body().string();
                        Toast.makeText(MainActivity.this, "Success the backend is connected"+responseBody,Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Log.e("An exception occurred",e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "an error occured in connecting with the backend" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}