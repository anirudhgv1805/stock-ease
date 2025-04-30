package com.anirudhgv.stockease.data.network;

import android.content.Context;

import com.anirudhgv.stockease.data.storage.SessionManager;
import com.anirudhgv.stockease.util.AuthInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiService apiService;
    private static  final String BASE_URL = "http://stockease.local/api/";

    public static ApiService getApiService(SessionManager sessionManager){
       if(apiService == null) {
           HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
           loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

           OkHttpClient client = new OkHttpClient.Builder()
                   .addInterceptor(new AuthInterceptor(sessionManager))
                   .addInterceptor(loggingInterceptor)
                   .build();

           Gson gson = new GsonBuilder()
                   .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                       @Override
                       public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                           return LocalDateTime.parse(json.getAsString());
                       }
                   })
                   .create();


           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .client(client)
                   .addConverterFactory(GsonConverterFactory.create(gson))
                   .build();

            apiService = retrofit.create(ApiService.class);
       }

       return apiService;
    }
}
