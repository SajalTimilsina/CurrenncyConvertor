package com.developer.sajalapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.sajalapplication.Retrofit.RetrofitBuilder;
import com.developer.sajalapplication.Retrofit.RetrofitInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Button button;
    EditText currencyToBeConverted;
    EditText currencyConverted;
    Spinner convertToDropdown;
    Spinner convertFromDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currencyConverted = findViewById(R.id.currency_converted);
        currencyToBeConverted = findViewById(R.id.currency_to_be_converted);
        convertToDropdown = findViewById(R.id.to);
        convertFromDropdown = findViewById(R.id.from);
        button = findViewById(R.id.button);


        String[] dropDownList = {"usd","btc","eur","eth","eos","ltc","bch","bnb","aud","bdt","ars","bhd","chf","bdt","cny","huf","gbp","idr","lkr","sgd","twd","uah"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertToDropdown.setAdapter(adapter);
        convertFromDropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency("exchange_rates");
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("responce","sdsdasas");
                       Log.d("responce",String.valueOf(response.body()));
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        String to = (convertToDropdown.getSelectedItem().toString()).toString();
                        String from = (convertFromDropdown.getSelectedItem().toString()).toString();

                        JsonObject valueto = rates.getAsJsonObject(to);
                        JsonObject valuefrom = rates.getAsJsonObject(from);
                        Double orgValueto = Double.valueOf(valueto.get("value").toString());
                        Double orgValuefrom = Double.valueOf(valuefrom.get("value").toString());

                        double currency = Double.valueOf(currencyToBeConverted.getText().toString());
                        Double result = (orgValueto*currency)/orgValuefrom;
                        currencyConverted.setText(String.valueOf(result));

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        System.out.println("onFailure"+call);
                        Log.d("responce",String.valueOf("Wrong"));
                        Log.d("responce",t.getCause()+"");
                        t.printStackTrace();
                        currencyConverted.setText("Network Error");
                    }
                });
            }
        });
    }
}





