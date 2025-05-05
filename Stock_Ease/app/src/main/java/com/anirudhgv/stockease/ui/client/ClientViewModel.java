package com.anirudhgv.stockease.ui.client;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anirudhgv.stockease.data.model.Order;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.repository.OrderRepository;
import com.anirudhgv.stockease.data.repository.ProductRepository;

import java.util.List;

public class ClientViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> orderSuccess = new MutableLiveData<>();

    public ClientViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        orderRepository = new OrderRepository(application);
    }

    // 📦 Fetch available products
    public void fetchProducts() {
        productRepository.getAllProducts().observeForever(productList::setValue);
    }
    public void placeOrder(Order order) {
        orderRepository.placeOrder(order).observeForever(orderSuccess::setValue);
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public LiveData<Boolean> getOrderSuccess() {
        return orderSuccess;
    }
}
