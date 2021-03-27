package com.example.baking_app_master.activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import com.example.baking_app_master.R;
import com.example.baking_app_master.Widget.BakingAppWidget;
import com.example.baking_app_master.adapters.IngredientAdapter;
import com.example.baking_app_master.api.RetrofitClient;
import com.example.baking_app_master.model.RecipesData.Ingredient;
import com.example.baking_app_master.model.RecipesData.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IngredientsDetailsActivity extends AppCompatActivity {

    Recipe recipe;
    List<Ingredient> ingredientList;
    IngredientAdapter ingredientAdapter;


    RecyclerView RecIngredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_details);
        Intent intent = getIntent();

        recipe = intent.getParcelableExtra("Recipe");
        String title = recipe.getName();
        setTitle(title);
        RecIngredient= findViewById(R.id.Rec_Ingredient);
        ingredientList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecIngredient.setLayoutManager(layoutManager);
        GetAllInredients();




    }

    public void WidgetAdd() {
        ArrayList<String> InPref = makeItString(ingredientList);
        setArrayPrefs("ingredients", InPref, IngredientsDetailsActivity.this);


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(IngredientsDetailsActivity.this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                new ComponentName(IngredientsDetailsActivity.this, BakingAppWidget.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);

        BakingAppWidget.updateAppWidget(IngredientsDetailsActivity.this, appWidgetManager, appWidgetIds);


        Toast.makeText(IngredientsDetailsActivity.this, "added to the widget", Toast.LENGTH_SHORT).show();
    }

    public void GetAllInredients() {
        Call<List<Recipe>> call = RetrofitClient.getInstance().getApi().get_recipe();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {


                ingredientList = recipe.getIngredients();


                ingredientAdapter = new IngredientAdapter(ingredientList, IngredientsDetailsActivity.this);
                RecIngredient.setAdapter(ingredientAdapter);
                WidgetAdd();

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(IngredientsDetailsActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public static void setArrayPrefs(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("appWidget", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.size());
        for (int i = 0; i < array.size(); i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    private ArrayList<String> makeItString(List<Ingredient> ingredientsModels) {
        ArrayList<String> ingredientsInPref = new ArrayList<>();
        for (int i = 0; i < ingredientsModels.size(); i++) {
            String ingre = (i + 1) + "-" + ingredientsModels.get(i).getQuantity()
                    + " " + ingredientsModels.get(i).getMeasure()
                    + " of " + ingredientsModels.get(i).getIngredient();
            ingredientsInPref.add(ingre);
        }
        return ingredientsInPref;
    }

    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("appWidget", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }


}
