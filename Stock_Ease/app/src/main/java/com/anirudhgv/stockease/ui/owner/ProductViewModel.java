package com.anirudhgv.stockease.ui.owner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.repository.ProductRepository;

public class ProductViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> createProduct(Product product) {
        return productRepository.createProduct(product);
    }
}
