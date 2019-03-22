package com.ohmstheresistance.tribute.network;

import com.ohmstheresistance.tribute.model.ButtonAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ButtonService {

    @GET("InquisitiveMindHasToKnow/a997da0d4b2032adebe4472869d851c3/raw/f59fbd773b5aecb136c0f6f208d42a1a69688d2d/MainActivityButtons")
    Call<ButtonAPI> getButtons();
}
