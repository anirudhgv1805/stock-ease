package com.anirudhgv.stockease.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditOrderActivity extends AppCompatActivity {

    private Spinner statusSpinner;
    private Button btnUpdateStatus;
    private ApiService apiService;
    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        statusSpinner = findViewById(R.id.spinnerStatus);
        btnUpdateStatus = findViewById(R.id.btnUpdateStatus);

        orderId = getIntent().getIntExtra("order_id", -1);
        if (orderId == -1) {
            Toast.makeText(this, "Invalid order ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        apiService = ApiClient.getApiService(sessionManager);

        String[] statuses = {"Pending", "Processing", "Completed"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        btnUpdateStatus.setOnClickListener(view -> {
            String selectedStatus = statusSpinner.getSelectedItem().toString();
            updateOrderStatus((long) orderId, selectedStatus);
        });
    }

    private void updateOrderStatus(Long id, String status) {
        Order updatedOrder = new Order();
        updatedOrder.setId((long) Math.toIntExact(id));
        updatedOrder.setStatus(status);

        apiService.updateOrder(Math.toIntExact(id), updatedOrder).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditOrderActivity.this, "Status updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditOrderActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditOrderActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
