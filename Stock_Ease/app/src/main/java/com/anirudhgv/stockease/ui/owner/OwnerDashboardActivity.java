package com.anirudhgv.stockease.ui.owner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.storage.SessionManager;

public class OwnerDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_company_dashboard);

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        Toast.makeText(this, "At OwnerDashboard", Toast.LENGTH_LONG).show();
    }
}
