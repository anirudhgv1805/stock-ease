package com.anirudhgv.stockease;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.data.storage.SessionManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.ClientDashboardActivity;
import com.anirudhgv.stockease.ui.client.*;
import com.anirudhgv.stockease.ui.employee.EmployeeDashboardActivity;
import com.anirudhgv.stockease.ui.login.LoginActivity;
import com.anirudhgv.stockease.ui.owner.OwnerDashboardActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Display display = getWindowManager().getDefaultDisplay();
        Display.Mode[] refreshRates = display.getSupportedModes();

        for (Display.Mode refreshRate : refreshRates) {
            Log.d("Supported Refresh Rates", "Rate: " + refreshRate);
        }

        for (Display.Mode refreshRate : refreshRates) {

            if (refreshRate.getRefreshRate() == 90.0f) {
                display.getSupportedModes();
                break;
            }
        }

        SessionManager sessionManager = new SessionManager(this);

        if (sessionManager.fetchAuthToken() != null && sessionManager.fetchUserRole() != null) {
            navigateBasedOnRole(sessionManager.fetchUserRole());
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }

    private void navigateBasedOnRole(String role) {
        Intent intent;
        switch (role.toLowerCase()) {
            case "client":
                intent = new Intent(this, ClientDashboardActivity.class);
                break;
            case "employee":
                intent = new Intent(this, EmployeeDashboardActivity.class);
                break;
            case "owner":
                intent = new Intent(this, OwnerDashboardActivity.class);
                break;
            default:
                intent = new Intent(this, LoginActivity.class);
                break;
        }

        startActivity(intent);
    }
}
