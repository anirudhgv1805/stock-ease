package com.anirudhgv.stockease.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.ui.adapter.TransactionAdapter;
import com.anirudhgv.stockease.ui.viewmodel.OrderViewModel;

public class TransactionActivity extends AppCompatActivity {

    private TransactionAdapter transactionAdapter;
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        RecyclerView recyclerView = findViewById(R.id.recyclerTransactions);
        transactionAdapter = new TransactionAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(transactionAdapter);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        orderViewModel.getOrderList().observe(this, orders -> {
            // Filter completed orders
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                transactionAdapter.setOrders(
                        orders.stream()
                                .filter(order -> "Completed".equalsIgnoreCase(order.getStatus()))
                                .toList()
                );
            }
        });

        orderViewModel.fetchOrders();
    }
}
