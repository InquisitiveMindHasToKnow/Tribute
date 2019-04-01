package com.ohmstheresistance.tribute.network;

import com.ohmstheresistance.tribute.model.ButtonAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ButtonService {

    @GET("InquisitiveMindHasToKnow/a997da0d4b2032adebe4472869d851c3/raw/c943c32c737bd72f420b34018347bc2b74c4f331/MainActivityButtons")
    Call<ButtonAPI> getButtons();
}
