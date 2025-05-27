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
import androidx.appcompat.view.menu.MenuView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.login.LoginActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.ApiStatus;
import org.w3c.dom.Text;

public class OwnerDashboardActivity extends AppCompatActivity {

    private OwnerViewModel ownerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        TextView inventoryOverviewBtn = findViewById(R.id.inventoryOverviewBtn);
        TextView manageUsersBtn = findViewById(R.id.manageUsersBtn);
        TextView createProductBtn = findViewById(R.id.createProductBtn);
        TextView manageOrdersBtn = findViewById(R.id.manageOrdersBtn);
        TextView manageProductsBtn = findViewById(R.id.manageProductsBtn);

        TextView pendingCount = findViewById(R.id.pendingCount);
        TextView processingCount = findViewById(R.id.processingCount);
        TextView completedCount = findViewById(R.id.completedCount);

        ownerViewModel = new ViewModelProvider(this).get(OwnerViewModel.class);
        ownerViewModel.refreshData();

        ownerViewModel.getOwnerDashboardData().observe(this, data -> {
            if (data != null) {
                pendingCount.setText(String.valueOf(data.getPendingOrders()));
                processingCount.setText(String.valueOf(data.getProcessingOrders()));
                completedCount.setText(String.valueOf(data.getCompletedOrders()));
            } else {
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
;
        inventoryOverviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, InventoryOverviewActivity.class));
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

        manageProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerDashboardActivity.this, ProductEditActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ownerViewModel.refreshData();
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
        Intent intent = new Intent(OwnerDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}
