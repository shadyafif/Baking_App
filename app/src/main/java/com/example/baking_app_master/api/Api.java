package com.example.baking_app_master.api;



import com.example.baking_app_master.model.RecipesData.Recipe;

import java.util.List;

import retrofit2.Call;


import retrofit2.http.GET;

public interface Api {

    @GET("baking.json")
    Call<List<Recipe>> get_recipe();




}
