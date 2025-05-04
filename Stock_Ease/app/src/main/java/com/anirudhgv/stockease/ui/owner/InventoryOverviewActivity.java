package com.anirudhgv.stockease.ui.owner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Inventory;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryOverviewActivity extends AppCompatActivity implements InventoryAdapter.onItemClickListener {

    private InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new InventoryAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        ApiService apiService = ApiClient.getApiService(new SessionManager(getApplicationContext())); // No need to pass the SessionManager

        apiService.getInventory().enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setInventoryList(response.body());
                } else {
                    Toast.makeText(InventoryOverviewActivity.this, "Failed to load inventory", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(InventoryOverviewActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemCLick(Inventory item) {
        Intent intent = new Intent(this,InventoryEditActivity.class);
        intent.putExtra("inventory_id", item.getInventoryId());
        intent.putExtra("product_id", item.getProduct().getId());
        intent.putExtra("product_name", item.getProduct().getName());
        intent.putExtra("product_sku", item.getProduct().getSku());
        intent.putExtra("product_description", item.getProduct().getDescription());
        intent.putExtra("product_price", item.getProduct().getPrice().toPlainString());
        intent.putExtra("quantity", item.getQuantity());
        startActivity(intent);
    }
}
