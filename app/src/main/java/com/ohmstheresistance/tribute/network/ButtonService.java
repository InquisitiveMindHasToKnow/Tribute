package com.ohmstheresistance.tribute.network;

import com.ohmstheresistance.tribute.model.ButtonAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ButtonService {

    @GET("InquisitiveMindHasToKnow/a997da0d4b2032adebe4472869d851c3/raw/88d1c27d747fe48f3f1cd895e9ad60de9c3ff4c5/MainActivityButtons")
    Call<ButtonAPI> getButtons();
}
