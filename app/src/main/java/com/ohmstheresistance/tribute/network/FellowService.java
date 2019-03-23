package com.ohmstheresistance.tribute.network;

import com.ohmstheresistance.tribute.model.FellowAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FellowService {
    @GET("InquisitiveMindHasToKnow/d6d44f5f812e5a17778f1765ec82f515/raw/b27dac3c1e815b906abc949582cfd501fb3ef56a/FellowListGist")
    Call<FellowAPI> getFellows();
}
