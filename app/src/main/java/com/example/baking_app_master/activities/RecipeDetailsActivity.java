package com.example.baking_app_master.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baking_app_master.R;
import com.example.baking_app_master.adapters.StepsAdapter;
import com.example.baking_app_master.api.RetrofitClient;
import com.example.baking_app_master.fragments.StepDetailsFragment;
import com.example.baking_app_master.model.RecipesData.Recipe;
import com.example.baking_app_master.model.RecipesData.Step;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.baking_app_master.adapters.Helper.Replace;

public class RecipeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    Recipe recipe;


    Step step;
    Button btnIngredients,btnSteps;
    RecyclerView REcSteps;
    ImageView iv_Receipt_Details;
    TextView tv_Steps_ReceiptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        InitView();


    }


    public void InitView() {
        Intent intent = getIntent();
        btnIngredients=findViewById(R.id.btn_Ingredients);
        btnSteps=findViewById(R.id.btn_Steps);
        recipe = intent.getParcelableExtra("RecipeDetails");
        String title = recipe.getName();
        setTitle(title);
        tv_Steps_ReceiptName = findViewById(R.id.tv_Steps_ReceiptName);
        tv_Steps_ReceiptName.setText(title);
        iv_Receipt_Details = findViewById(R.id.iv_Receipt_Details);
        int mId = recipe.getId();
        String imageUrl = recipe.getImage();
        if (imageUrl != null && imageUrl.isEmpty()) {
            switch (mId) {
                //Nutella Pie
                case 1:

                    Picasso.get().load(R.drawable.nutella_pie).into(iv_Receipt_Details);
                    break;
                //Brownies
                case 2:
                    Picasso.get().load(R.drawable.brownies).into(iv_Receipt_Details);
                    break;
                //Yellow Cake
                case 3:
                    Picasso.get().load(R.drawable.yellow_cake).into(iv_Receipt_Details);
                    break;
                //Cheesecake
                case 4:
                    Picasso.get().load(R.drawable.cheese_cake).into(iv_Receipt_Details);

                    break;
            }
        } else {
            Picasso.get().load(imageUrl).into(iv_Receipt_Details);

        }
        btnIngredients.setOnClickListener(this);
        btnSteps.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btn_Ingredients):
                Intent intent = new Intent(RecipeDetailsActivity.this, IngredientsDetailsActivity.class);
                intent.putExtra("Recipe", recipe);
                startActivity(intent);
                break;
            case (R.id.btn_Steps):
                Intent intentSteps = new Intent(RecipeDetailsActivity.this, StepsAcivity.class);
                intentSteps.putExtra("Recipe", recipe);
                startActivity(intentSteps);
                break;
        }
    }
}

