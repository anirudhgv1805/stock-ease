package com.anirudhgv.stockease.ui.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.login.LoginActivity;
import com.anirudhgv.stockease.ui.owner.CreateProductActivity;
import com.anirudhgv.stockease.ui.owner.InventoryOverviewActivity;
import com.anirudhgv.stockease.ui.owner.ManageOrdersActivity;
import com.anirudhgv.stockease.ui.owner.ManageUsersActivity;
import com.anirudhgv.stockease.ui.owner.ProductEditActivity;
import com.anirudhgv.stockease.ui.owner.ViewReportsActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class StaffDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        TextView inventoryOverviewBtn = findViewById(R.id.inventoryOverviewBtn);
        TextView createProductBtn = findViewById(R.id.createProductBtn);
        TextView manageOrdersBtn = findViewById(R.id.manageOrdersBtn);
        TextView manageProductsBtn = findViewById(R.id.manageProductsBtn);

        TextView pendingCount = findViewById(R.id.pendingCount);
        TextView processingCount = findViewById(R.id.processingCount);
        TextView completedCount = findViewById(R.id.completedCount);
        TextView shippedCount = findViewById(R.id.shippedCount);
        TextView cancelledCount = findViewById(R.id.cancelledCount);
        
        setSupportActionBar(topAppBar);

        inventoryOverviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffDashboardActivity.this, InventoryOverviewActivity.class));
            }
        });

        createProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffDashboardActivity.this, CreateProductActivity.class));
            }
        });

        manageOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffDashboardActivity.this, ManageOrdersActivity.class));
            }
        });

        manageProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffDashboardActivity.this, ProductEditActivity.class));
            }
        });

        Toast.makeText(this, "It is at Employee dashboard", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.owner_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        int id = menu.getItemId();

        if(id == R.id.nav_logout){
            handleLogout();
        }
        if(id == R.id.nav_profile){
            return true;
        }
        return true;
    }

    private void handleLogout(){
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.clearSession();
        Intent intent = new Intent(StaffDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
