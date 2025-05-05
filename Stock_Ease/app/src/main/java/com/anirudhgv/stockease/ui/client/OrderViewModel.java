package com.anirudhgv.stockease.ui.client;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.repository.OrderRepository;
import com.anirudhgv.stockease.data.repository.ProductRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public OrderViewModel(Application application) {
        super(application);
        productRepository = new ProductRepository(application.getApplicationContext());
        orderRepository = new OrderRepository(application.getApplicationContext());
    }

    public void fetchProducts() {
        productRepository.getAllProducts().observeForever(products::setValue);
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<Boolean> placeOrder(Order order) {
        return orderRepository.placeOrder(order);
    }
}
