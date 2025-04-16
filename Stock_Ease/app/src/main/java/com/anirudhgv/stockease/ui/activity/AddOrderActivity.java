package com.anirudhgv.stockease.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.ui.viewmodel.AddOrderViewModel;

public class AddOrderActivity extends AppCompatActivity {

    private EditText etItemName, etQuantity, etClientName;
    private AddOrderViewModel addOrderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        etItemName = findViewById(R.id.etItemName);
        etQuantity = findViewById(R.id.etQuantity);
        etClientName = findViewById(R.id.etClientName);
        Button btnSubmit = findViewById(R.id.btnSubmitOrder);

        addOrderViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(AddOrderViewModel.class);

        btnSubmit.setOnClickListener(v -> {
            String item = etItemName.getText().toString();
            int quantity = Integer.parseInt(etQuantity.getText().toString());
            String client = etClientName.getText().toString();

            addOrderViewModel.submitOrder(item, quantity, client);
        });

        addOrderViewModel.getOrderAdded().observe(this, success -> {
            if (success != null && success) {
                Toast.makeText(this, "Order submitted", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Submission failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
