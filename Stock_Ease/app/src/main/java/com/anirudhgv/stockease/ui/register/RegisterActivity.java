package com.anirudhgv.stockease.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.dto.UserDto;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.ClientDashboardActivity;
import com.anirudhgv.stockease.ui.employee.StaffDashboardActivity;
import com.anirudhgv.stockease.ui.owner.OwnerDashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel viewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        sessionManager = new SessionManager(this);

        TextInputEditText nameEditText = findViewById(R.id.nameEditText);
        TextInputEditText emailEditText = findViewById(R.id.emailEditText);
        TextInputEditText passwordEditText = findViewById(R.id.passwordEditText);
        MaterialButton registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String name = Objects.requireNonNull(nameEditText.getText()).toString().trim();
            String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
            String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            UserDto userDto = new UserDto(name, email, password);
            System.out.println(userDto);
            viewModel.register(userDto, sessionManager);
        });

        viewModel.getRegisterSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                navigateBasedOnRole(sessionManager.fetchUserRole());
            }
        });

        viewModel.getErrorMessage().observe(this, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }

    private void navigateBasedOnRole(String role) {
        Intent intent;
        switch (role.toLowerCase()) {
            case "client":
                intent = new Intent(this, ClientDashboardActivity.class);
                break;
            case "employee":
                intent = new Intent(this, StaffDashboardActivity.class);
                break;
            case "owner":
                intent = new Intent(this, OwnerDashboardActivity.class);
                break;
            default:
                Toast.makeText(this, "Unknown role", Toast.LENGTH_LONG).show();
                return;
        }
        startActivity(intent);
        finish();
    }
}
