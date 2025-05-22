package com.anirudhgv.stockease.ui.client;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.model.OrderItem;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.model.User;
import com.anirudhgv.stockease.data.model.dto.UserDto;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.client.ProductAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClientDashboardActivity extends AppCompatActivity {

    private OrderViewModel orderViewModel;
    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;
    private Button placeOrderButton;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);

        sessionManager = new SessionManager(getApplicationContext());
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        recyclerView = findViewById(R.id.product_recyclerview);
        placeOrderButton = findViewById(R.id.place_order_button);

        productAdapter = new ProductAdapter(this, productList, product ->
                Toast.makeText(this, "Clicked: " + product.getName(), Toast.LENGTH_SHORT).show());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        orderViewModel.getProducts().observe(this, products -> {
            if (products != null) {
                productList.clear();
                productList.addAll(products);
                productAdapter.notifyDataSetChanged();
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

        UserDto user = new UserDto();
        if(sessionManager !=null) {
            user.setId(sessionManager.fetchUserId());
        }
        Order order = new Order();
        order.setUser(user);
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setStatus(Order.Status.PENDING);
        

        orderViewModel.placeOrder(order).observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Order Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
