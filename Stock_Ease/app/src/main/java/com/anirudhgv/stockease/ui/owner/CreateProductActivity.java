package com.anirudhgv.stockease.ui.owner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.repository.ProductRepository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

public class CreateProductActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_product);


        TextInputEditText nameInput = findViewById(R.id.productNameInput);
        TextInputEditText descInput = findViewById(R.id.productDescriptionInput);
        TextInputEditText skuInput = findViewById(R.id.productSkuInput);
        TextInputEditText priceInput = findViewById(R.id.productPriceInput);
        MaterialButton createBtn = findViewById(R.id.createProductButton);


        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);


        createBtn.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            String desc = descInput.getText().toString().trim();
            String sku = skuInput.getText().toString().trim();
            String priceStr = priceInput.getText().toString().trim();

            if (name.isEmpty() || desc.isEmpty() || sku.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal price;
            try {
                price = new BigDecimal(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(name, desc, sku, price);
            productViewModel.createProduct(product).observe(this, success -> {
                if (success) {
                    Toast.makeText(this, "Product created successfully", Toast.LENGTH_LONG).show();
                    finish(); // Optional: close the activity
                } else {
                    Toast.makeText(this, "Failed to create product", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
