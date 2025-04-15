package com.anirudhgv.stockease;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.ui.adapter.OrderAdapter;
import com.anirudhgv.stockease.ui.viewmodel.OrderViewModel;

public class MainActivity extends AppCompatActivity {

    private OrderAdapter orderAdapter;
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOrders = findViewById(R.id.btnOrders);
        RecyclerView recyclerOrders = findViewById(R.id.recyclerOrders);

        orderAdapter = new OrderAdapter();
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrders.setAdapter(orderAdapter);

        orderViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(OrderViewModel.class);

        orderViewModel.getOrderList().observe(this,orders -> {
            orderAdapter.setOrders(orders);
        });

        btnOrders.setOnClickListener(v -> {
            orderViewModel.fetchOrders();
        });
    }
}