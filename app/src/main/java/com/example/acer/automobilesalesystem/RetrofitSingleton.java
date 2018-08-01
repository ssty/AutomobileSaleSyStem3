package com.example.acer.automobilesalesystem;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    public static RetrofitSingleton RetrofitSingleton = null;
    private static Retrofit retrofit = null;
    private static ApiService apiService = null;

    private RetrofitSingleton() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://automobile-sales.herokuapp.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService(){
        if (RetrofitSingleton == null){
            RetrofitSingleton = new RetrofitSingleton();
        }
        return apiService;
    }

    public static Retrofit getRetrofit(){
        if (RetrofitSingleton == null){
            RetrofitSingleton = new RetrofitSingleton();
        }
        return retrofit;
    }
}
