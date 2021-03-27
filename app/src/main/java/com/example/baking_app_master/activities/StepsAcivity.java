package com.example.baking_app_master.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.baking_app_master.R;
import com.example.baking_app_master.adapters.StepsAdapter;
import com.example.baking_app_master.api.RetrofitClient;
import com.example.baking_app_master.fragments.StepDetailsFragment;
import com.example.baking_app_master.model.RecipesData.Recipe;
import com.example.baking_app_master.model.RecipesData.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.baking_app_master.adapters.Helper.Replace;

public class StepsAcivity extends AppCompatActivity {

    Recipe recipe;
    List<Step> RecipeList;
    StepsAdapter stepsAdapter;
    public boolean isTwoPane;
    RecyclerView REcSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_acivity);
        Intent intent = getIntent();

        recipe = intent.getParcelableExtra("Recipe");
        String title = recipe.getName();
        setTitle(title);
        RecipeList=recipe.getSteps();
        if (findViewById(R.id.detailContainer) != null) {
            isTwoPane = true;

        } else {
            isTwoPane = false;

        }

        if (isTwoPane) {

            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            Replace(stepDetailsFragment, R.id.detailContainer, getSupportFragmentManager().beginTransaction());
        }





        REcSteps = findViewById(R.id.REc_Steps);
        RecipeList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        REcSteps.setLayoutManager(layoutManager);

        GetAllSteps();
    }

    public void GetAllSteps() {
        Call<List<Recipe>> call = RetrofitClient.getInstance().getApi().get_recipe();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                RecipeList = recipe.getSteps();
                stepsAdapter = new StepsAdapter(getApplication(), RecipeList);
                REcSteps.setAdapter(stepsAdapter);


            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getApplication(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}