package com.ohmstheresistance.tribute.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ButtonRetrofit {

        private static Retrofit buttonRetrofitInstance;

        private ButtonRetrofit(){}

        public static Retrofit getRetrofitInstance() {
            if (buttonRetrofitInstance != null) {
                return buttonRetrofitInstance;
            }
            buttonRetrofitInstance = new Retrofit
                    .Builder()
                    .baseUrl("https://gist.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return buttonRetrofitInstance;
        }
    }
