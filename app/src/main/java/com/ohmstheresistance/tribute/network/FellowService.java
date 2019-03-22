package com.ohmstheresistance.tribute.network;

import com.ohmstheresistance.tribute.model.ButtonAPI;
import com.ohmstheresistance.tribute.model.FellowAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FellowService {
    @GET("InquisitiveMindHasToKnow/d6d44f5f812e5a17778f1765ec82f515/raw/04aa000602167253a536a506efa6a36775f9d140/FellowListGist")
    Call<FellowAPI> getFellows();
}
