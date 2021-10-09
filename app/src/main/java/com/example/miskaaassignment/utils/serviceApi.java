package com.example.miskaaassignment.utils;

import com.example.miskaaassignment.model.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface serviceApi {

    @GET("crew")
    Call<List<Crew>> getInformation();

}
