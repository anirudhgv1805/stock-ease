package com.anirudhgv.stockease.ui.owner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Inventory;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryEditActivity extends AppCompatActivity {

    private Inventory inventoryItem;
    private Product product;
    private TextView productNameTextView;
    private EditText quantityEditText;
    private EditText reasonEditText;
    private Button saveButton;
    private Long userId = (long)16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_edit);

        productNameTextView = findViewById(R.id.textViewProductName);
        quantityEditText = findViewById(R.id.editTextQuantity);
        reasonEditText = findViewById(R.id.editTextReason);
        saveButton = findViewById(R.id.buttonSave);

        long inventoryId = getIntent().getLongExtra("inventory_id", -1);
        long productId = getIntent().getLongExtra("product_id", -1);
        String name = getIntent().getStringExtra("product_name");
        String sku = getIntent().getStringExtra("product_sku");
        String description = getIntent().getStringExtra("product_description");
        String priceStr = getIntent().getStringExtra("product_price");
        int quantity = getIntent().getIntExtra("quantity", 0);

        inventoryItem = new Inventory(inventoryId, product, quantity, null);


        BigDecimal price = new BigDecimal(priceStr);

        if (inventoryItem != null) {
            product = new Product(productId, name, description, sku, price, null);
            System.out.println("At inventory edit" + product);
            if (product != null) {
                productNameTextView.setText(product.getName());
            }
            quantityEditText.setText(String.valueOf(inventoryItem.getQuantity()));
        } else {
            Toast.makeText(this, "No inventory item found", Toast.LENGTH_SHORT).show();
            finish();
        }

        saveButton.setOnClickListener(view -> {
            String newQuantityStr = quantityEditText.getText().toString().trim();
            if (!newQuantityStr.isEmpty()) {
                Toast.makeText(this, "Quantity updated locally", Toast.LENGTH_SHORT).show();
                updateInventory();
                finish();
            } else {
                Toast.makeText(this, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateInventory() {

        ApiService apiService = ApiClient.getApiService(new SessionManager(getApplicationContext())); // No need to pass the SessionManager

        String newQuantityStr = quantityEditText.getText().toString().trim();
        String reason = reasonEditText.getText().toString().trim();

        if (newQuantityStr.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int newQuantity = Integer.parseInt(newQuantityStr);
        System.out.println("newQuantity:" + newQuantity+"already in"+inventoryItem.getQuantity());
        int quantityChange = newQuantity - inventoryItem.getQuantity();

        if (quantityChange == 0) {
            Toast.makeText(this, "No change in quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.updateInventory(
                product.getId(),
                quantityChange,
                reason,
                userId
        ).enqueue(new Callback<Inventory>() {
            @Override
            public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(InventoryEditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inventory> call, Throwable t) {
                Toast.makeText(InventoryEditActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
