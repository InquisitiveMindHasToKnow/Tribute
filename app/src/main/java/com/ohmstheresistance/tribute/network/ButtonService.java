package com.ohmstheresistance.tribute.network;

import com.ohmstheresistance.tribute.model.ButtonAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ButtonService {

    @GET("InquisitiveMindHasToKnow/a997da0d4b2032adebe4472869d851c3/raw/0ce81a3deb5391a7ff2e8aa9dd218e8ba8234227/MainActivityButtons")
    Call<ButtonAPI> getButtons();
}
