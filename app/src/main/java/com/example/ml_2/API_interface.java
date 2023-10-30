package com.example.ml_2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API_interface {
    @GET("api/character")
    Call<CharactersList> getInfo();
}
