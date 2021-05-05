package com.developer.sajalapplication.Retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("api/v3/{currency}")
    Call<JsonObject> getExchangeCurrency(@Path("currency") String currency);
}
