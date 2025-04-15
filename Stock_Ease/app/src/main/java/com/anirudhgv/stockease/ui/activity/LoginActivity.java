package com.anirudhgv.stockease.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.MainActivity;
import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.ui.viewmodel.AuthViewModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername,etPassword;
    private Button btnLogin;
    private  AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        authViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(AuthViewModel.class);

        btnLogin.setOnClickListener(v -> {
           String username = etUsername.getText().toString().trim();
           String password = etPassword.getText().toString().trim();
           authViewModel.login(username,password);
        });

        authViewModel.getLoginResult().observe(this, success -> {
            if(success) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login Failed",  Toast.LENGTH_LONG).show();
            }
        });

        ApiService apiService = ApiClient.getApiService(new SessionManager(getApplicationContext()));

        apiService.testApi().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        if (response.body() == null) throw new AssertionError();
                        String responseBody = response.body().string();
                        Toast.makeText(LoginActivity.this,"The backend is connected successfully",Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Log.e("An exception has occurred",e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"An error occurred while trying to connect to the backend"+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Internet","API Connection Failed",t);
            }
        });
    }
}

