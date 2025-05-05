package com.anirudhgv.stockease.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.model.OrderItem;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.ui.client.OrderViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClientDashboardActivity extends AppCompatActivity {

    private OrderViewModel orderViewModel;
    private List<Product> productList = new ArrayList<>();
    private Button placeOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);


        placeOrderButton = findViewById(R.id.place_order_button);

        orderViewModel.getProducts().observe(this, products -> {
            if (products != null) {
                productList.clear();
                productList.addAll(products);

            }
        });


        orderViewModel.fetchProducts();


        placeOrderButton.setOnClickListener(v -> placeOrder());
    }

    private void placeOrder() {

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;


        for (Product product : productList) {
            int quantity = 1;
            BigDecimal priceAtOrder = product.getPrice();
            OrderItem orderItem = new OrderItem(null, null, product, quantity, priceAtOrder);
            orderItems.add(orderItem);
            totalAmount = totalAmount.add(priceAtOrder.multiply(BigDecimal.valueOf(quantity)));
        }


        Order order = new Order();
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setStatus(Order.Status.PENDING);


        orderViewModel.placeOrder(order).observe(this, success -> {
            if (success) {
                Toast.makeText(ClientDashboardActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ClientDashboardActivity.this, "Failed to place order", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
