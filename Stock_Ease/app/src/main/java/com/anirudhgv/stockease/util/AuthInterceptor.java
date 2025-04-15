package com.anirudhgv.stockease.util;

import androidx.annotation.NonNull;

import com.anirudhgv.stockease.data.storage.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final SessionManager sessionManager;

    public AuthInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String token = sessionManager.fetchAuthToken();

        if (token == null ) return chain.proceed(originalRequest);

        Request newRequest = originalRequest.newBuilder()
                .addHeader("Authorization","Bearer " + token)
                .build();

        return chain.proceed(newRequest);
    }
}
