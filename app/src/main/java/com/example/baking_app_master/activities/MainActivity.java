package com.example.baking_app_master.activities;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.example.baking_app_master.R;
import com.example.baking_app_master.adapters.AllRecipesAdapter;
import com.example.baking_app_master.api.RetrofitClient;
import com.example.baking_app_master.model.RecipesData.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    public List<Recipe> RecipeList;
    public AllRecipesAdapter allRecipesAdapter;
    LinearLayoutManager mLinearLayoutManager = null;
    GridLayoutManager mGridlayoutManager = null;
    RecyclerView MainRecRecipe;
    private boolean tabletFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainRecRecipe = findViewById(R.id.Main_Rec_Recipe);
        RecipeList = new ArrayList<>();
        GetAllRecipes();
        initializeScreen();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void GetAllRecipes() {
        Call<List<Recipe>> call = RetrofitClient.getInstance().getApi().get_recipe();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {


                RecipeList = response.body();


                allRecipesAdapter = new AllRecipesAdapter(MainActivity.this, RecipeList);
                MainRecRecipe.setAdapter(allRecipesAdapter);

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void initializeScreen() {
        //mobile case
        if (!isTablet(this)) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                mLinearLayoutManager = new GridLayoutManager(this, 1);
            else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                mGridlayoutManager = new GridLayoutManager(this, 2);
        }
        //tablet case
        else {
            tabletFlag = true;
            mGridlayoutManager = new GridLayoutManager(this, 2);
        }


        if (!tabletFlag) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                MainRecRecipe.setLayoutManager(mLinearLayoutManager);
            else
                MainRecRecipe.setLayoutManager(mGridlayoutManager);
        } else {
            MainRecRecipe.setLayoutManager(mGridlayoutManager);
        }


        MainRecRecipe.setHasFixedSize(true);
        AllRecipesAdapter recipeAdapter = new AllRecipesAdapter(this, RecipeList);
        MainRecRecipe.setAdapter(recipeAdapter);
    }

    /**
     * method will check if the device that the app runs on is a tablet or a mobile phone
     *
     * @param context app context
     * @return true if it is a tablet,false otherwise
     */
    private boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }


}
