package com.anirudhgv.stockease.data.network;

import android.content.Context;

import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.util.AuthInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiService apiService;
    private static  final String BASE_URL = "http://192.168.137.51:5050/";

    public static ApiService getApiService(SessionManager sessionManager){
       if(apiService == null) {
           HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
           loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

           OkHttpClient client = new OkHttpClient.Builder()
                   .addInterceptor(new AuthInterceptor(sessionManager))
                   .addInterceptor(loggingInterceptor)
                   .build();

           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .client(client)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();

            apiService = retrofit.create(ApiService.class);
       }

       return apiService;
    }
}
