package com.anirudhgv.stockease.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.dto.UserDto;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.ClientDashboardActivity;
import com.anirudhgv.stockease.ui.employee.StaffDashboardActivity;
import com.anirudhgv.stockease.ui.owner.OwnerDashboardActivity;
import com.anirudhgv.stockease.ui.register.RegisterActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        TextInputEditText emailEditText = findViewById(R.id.emailEditText);
        TextInputEditText passwordEditText = findViewById(R.id.passwordEditText);
        MaterialButton loginButton = findViewById(R.id.loginButton);
        TextView registerLink = findViewById(R.id.registerLink);

        loginButton.setOnClickListener(v -> {
            String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
            String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                UserDto u1 = new UserDto(email, password);
                viewModel.login(u1, sessionManager);
                System.out.println(u1);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Login Failed"+viewModel.getErrorMessage(),Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getLoginSuccess().observe(this, success -> {
            if (success) {
                String role = sessionManager.fetchUserRole();
                if (role == null) {
                    Toast.makeText(this, "Login successful but role missing", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Login successful as " + role, Toast.LENGTH_SHORT).show();
                navigateBasedOnRole(role);
            }
        });


        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loginButton.setEnabled(!isLoading);
                loginButton.setAlpha(isLoading ? 0.5f : 1f); // Optional: dim button
            }
        });

        
        viewModel.getErrorMessage().observe(this, msg ->
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());

        registerLink.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void navigateBasedOnRole(String role) {
        Intent intent;
        switch (role.toLowerCase()) {
            case "client":
                intent = new Intent(this, ClientDashboardActivity.class);
                break;
            case "staff":
                intent = new Intent(this, StaffDashboardActivity.class);
                break;
            case "owner":
                intent = new Intent(this, OwnerDashboardActivity.class);
                break;
            default:
                Toast.makeText(this, "Unknown role: " + role, Toast.LENGTH_LONG).show();
                return;
        }

        startActivity(intent);
    }
}
