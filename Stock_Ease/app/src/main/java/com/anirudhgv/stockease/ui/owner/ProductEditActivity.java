package com.anirudhgv.stockease.ui.owner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductEditActivity extends AppCompatActivity {

    private Spinner spinnerProduct;
    private EditText editName, editSku, editPrice;
    private Button btnUpdate;
    private List<Product> productList;
    private Product selectedProduct;

    private ApiService apiService;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        spinnerProduct = findViewById(R.id.spinnerProduct);
        editName = findViewById(R.id.editName);
        editSku = findViewById(R.id.editSku);
        editPrice = findViewById(R.id.editPrice);
        btnUpdate = findViewById(R.id.btnUpdateProduct);

        sessionManager = new SessionManager(this);
        apiService = ApiClient.getApiService(sessionManager);

        loadProducts();

        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = productList.get(position);
                editName.setText(selectedProduct.getName());
                editSku.setText(selectedProduct.getSku());
                editPrice.setText(String.valueOf(selectedProduct.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnUpdate.setOnClickListener(v -> {
            v.setClickable(false);
            selectedProduct.setName(editName.getText().toString());
            selectedProduct.setSku(editSku.getText().toString());
            selectedProduct.setPrice(BigDecimal.valueOf(Double.parseDouble(editPrice.getText().toString())));
            selectedProduct.setCreatedAt(LocalDateTime.now());

            apiService.updateProduct(selectedProduct.getId(), selectedProduct)
                    .enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(ProductEditActivity.this, "Product updated!", Toast.LENGTH_SHORT).show();
                                v.setClickable(true);
                            } else {
                                Toast.makeText(ProductEditActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            Toast.makeText(ProductEditActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void loadProducts() {
        apiService.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductEditActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            productList.stream().map(Product::getSku).collect(Collectors.toList()));
                    spinnerProduct.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductEditActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

