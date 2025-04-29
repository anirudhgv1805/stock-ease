package com.anirudhgv.stockease.ui.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class OwnerDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        TextView inventoryOverviewBtn = findViewById(R.id.inventoryOverviewBtn);
        TextView viewReportsBtn = findViewById(R.id.viewReportsBtn);
        TextView manageUsersBtn = findViewById(R.id.manageUsersBtn);
        TextView createProductBtn = findViewById(R.id.createProductBtn);
        TextView manageOrdersBtn = findViewById(R.id.manageOrdersBtn);

        TextView pendingCount = findViewById(R.id.pendingCount);
        TextView processingCount = findViewById(R.id.processingCount);
        TextView completedCount = findViewById(R.id.completedCount);

        OwnerViewModel ownerViewModel = new ViewModelProvider(this).get(OwnerViewModel.class);
        ownerViewModel.loadOwnerDashboardData();

        ownerViewModel.getOwnerDashboardData().observe(this, data -> {
            if (data != null) {
                pendingCount.setText(String.valueOf(data.getPendingOrders()));
                processingCount.setText(String.valueOf(data.getProcessingOrders()));
                completedCount.setText(String.valueOf(data.getCompletedOrders()));
            } else {
                // Optionally show error message or placeholder
                pendingCount.setText("—");
                processingCount.setText("—");
                completedCount.setText("—");
            }
        });

        setSupportActionBar(topAppBar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                topAppBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Toast.makeText(this, "At OwnerDashboard", Toast.LENGTH_LONG).show();

        inventoryOverviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, InventoryOverviewActivity.class));
            }
        });

        viewReportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, ViewReportsActivity.class));
            }
        });

        manageUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, ManageUsersActivity.class));
            }
        });

        createProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, CreateProductActivity.class));
            }
        });

        manageOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, ManageOrdersActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.owner_nav_menu, menu);
        return true;
    }
}
