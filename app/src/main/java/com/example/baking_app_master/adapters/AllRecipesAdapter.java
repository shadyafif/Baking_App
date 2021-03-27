package com.example.baking_app_master.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baking_app_master.R;
import com.example.baking_app_master.activities.RecipeDetailsActivity;
import com.example.baking_app_master.model.RecipesData.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllRecipesAdapter extends RecyclerView.Adapter<AllRecipesAdapter.ViewHolder> {
    Context context;
    List<Recipe> RecipeList;


    public AllRecipesAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        RecipeList = recipeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipecardview, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Recipe recipe = RecipeList.get(position);
        int mId = recipe.getId();
        viewHolder.txtRecipeName.setText(recipe.getName());
        String imageUrl = recipe.getImage();

        if (imageUrl != null && imageUrl.isEmpty()) {
            switch (mId) {
                //Nutella Pie
                case 1:

                    Picasso.get().load(R.drawable.nutella_pie).into(viewHolder.recipe_item_Img);
                    break;
                //Brownies
                case 2:
                    Picasso.get().load(R.drawable.brownies).into(viewHolder.recipe_item_Img);
                    break;
                //Yellow Cake
                case 3:
                    Picasso.get().load(R.drawable.yellow_cake).into(viewHolder.recipe_item_Img);
                    break;
                //Cheesecake
                case 4:
                    Picasso.get().load(R.drawable.cheese_cake).into(viewHolder.recipe_item_Img);

                    break;
            }
        } else {
            Picasso.get().load(imageUrl).into(viewHolder.recipe_item_Img);

        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra("RecipeDetails", recipe);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return RecipeList != null ? RecipeList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView recipe_item_Img;
        public TextView txtRecipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_item_Img = itemView.findViewById(R.id.recipe_item_Img);
            txtRecipeName = itemView.findViewById(R.id.txtRecipeName);

        }
    }
}
