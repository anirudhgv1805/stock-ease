package com.anirudhgv.stockease.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.ui.adapter.TransactionAdapter;
import com.anirudhgv.stockease.ui.viewmodel.OrderViewModel;
import com.anirudhgv.stockease.data.model.Order;

import java.util.ArrayList;
import java.util.List;

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
            List<Order> completedOrders = new ArrayList<>();
            for (Order order : orders) {
                if ("Completed".equalsIgnoreCase(order.getStatus())) {
                    completedOrders.add(order);
                }
            }
            transactionAdapter.setOrders(completedOrders);
        });

        orderViewModel.fetchOrders();
    }
}
