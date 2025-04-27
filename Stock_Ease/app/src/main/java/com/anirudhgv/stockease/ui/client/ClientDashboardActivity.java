package com.anirudhgv.stockease.ui.client;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.login.LoginActivity;
import com.google.android.material.button.MaterialButton;

public class ClientDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_client_dashboard);

        MaterialButton logout = findViewById(R.id.logout);

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        logout.setOnClickListener(v -> {
            sessionManager.clearSession();
            Toast.makeText(this,"User LoggedOut Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        });


        Toast.makeText(this, "At ClientDashboard", Toast.LENGTH_LONG).show();
    }


}
