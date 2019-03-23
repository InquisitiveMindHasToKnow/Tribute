package com.ohmstheresistance.tribute.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FellowAPI {

    @SerializedName("message")
    private List<Fellows> fellows;

    public List<Fellows> getFellows(){
        return fellows;
    }
}
