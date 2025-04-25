package com.anirudhgv.stockease.ui.dashboard.company;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.storage.SessionManager;

public class CompanyDashBoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_dashboard);

        SessionManager sessionManager = new SessionManager(getApplicationContext());


        Toast.makeText(this, "At company dashboard", Toast.LENGTH_LONG).show();
    }
}
