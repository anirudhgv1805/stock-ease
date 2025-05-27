package com.anirudhgv.stockease.ui.owner;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.model.dto.StatusUpdateDto;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    private Spinner spinnerStatus;
    private Button buttonSave;
    private TextView textOrderId, textUser, textTotalAmount;

    private Order order;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        textOrderId = findViewById(R.id.textOrderId);
        textUser = findViewById(R.id.textUser);
        textTotalAmount = findViewById(R.id.textTotalAmount);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        buttonSave = findViewById(R.id.buttonSave);

        order = getIntent().getParcelableExtra("order");

        if (order == null) {
            Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        apiService = ApiClient.getApiService(sessionManager);

        populateOrderDetails();
        setupStatusSpinner();

        buttonSave.setOnClickListener(v -> updateOrderStatus());
    }

    private void populateOrderDetails() {
        textOrderId.setText("Order ID: #" + order.getOrderId());
        System.out.println(order.getUser().getUsername());
        textUser.setText("User: " + (order.getUser().getUsername() != null ? order.getUser().getUsername() : "Unknown"));
        textTotalAmount.setText("Total: ₹" + order.getTotalAmount().toPlainString());
    }

    private void setupStatusSpinner() {
        Order.Status[] statuses = Order.Status.values();
        ArrayAdapter<Order.Status> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, statuses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        // Select current status
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i] == order.getStatus()) {
                spinnerStatus.setSelection(i);
                break;
            }
        }
    }

    private void updateOrderStatus() {
        Order.Status selectedStatus = (Order.Status) spinnerStatus.getSelectedItem();

        if (selectedStatus == order.getStatus()) {
            Toast.makeText(this, "Status is unchanged", Toast.LENGTH_SHORT).show();
            return;
        }

        StatusUpdateDto dto = new StatusUpdateDto(selectedStatus.name());

        apiService.updateOrderStatus(order.getOrderId(), dto).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(OrderDetailActivity.this, "Order status updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(OrderDetailActivity.this, "Failed to update status", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(OrderDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("OrderDetail", "Update failed", t);
            }
        });
    }
}
