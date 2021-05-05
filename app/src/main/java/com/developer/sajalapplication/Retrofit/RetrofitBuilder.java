package com.developer.sajalapplication.Retrofit;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        try {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.coingecko.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            return retrofit;
        }catch (Exception ex){
            Log.d("responce",ex.toString());
        }
        return retrofit;
    }
}
