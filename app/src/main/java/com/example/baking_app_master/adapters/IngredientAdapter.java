package com.example.baking_app_master.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baking_app_master.R;
import com.example.baking_app_master.model.RecipesData.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    List<Ingredient> ingredientList;
    Context context;

    public IngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_cardview, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int poisiton) {
        Ingredient ingredient = ingredientList.get(poisiton);
        viewHolder.txt_ingredient_Quantity.setText(ingredient.getQuantity() + "");
        viewHolder.txt_ingredient_measure.setText(ingredient.getMeasure());
        viewHolder.txt_ingredient_ingredient.setText(ingredient.getIngredient());
        String Measure = ingredient.getMeasure();

        switch (Measure) {

            case "CUP":
                Picasso.get().load(R.drawable.cup).into(viewHolder.iv_measure);
                break;

            case "TBLSP":
                Picasso.get().load(R.drawable.spoon).into(viewHolder.iv_measure);
                break;
            case "TSP":
                Picasso.get().load(R.drawable.spoon).into(viewHolder.iv_measure);
                break;

            case "K" :
                Picasso.get().load(R.drawable.images).into(viewHolder.iv_measure);

                break;
            case "G":
                Picasso.get().load(R.drawable.images).into(viewHolder.iv_measure);

                break;
            case "OZ":
                Picasso.get().load(R.drawable.oz).into(viewHolder.iv_measure);

                break;
            case "UNIT":
                Picasso.get().load(R.drawable.number).into(viewHolder.iv_measure);

                break;
        }
    }


    @Override
    public int getItemCount() {
        return ingredientList != null ? ingredientList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_ingredient_Quantity, txt_ingredient_measure, txt_ingredient_ingredient;
        ImageView iv_measure;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ingredient_Quantity = itemView.findViewById(R.id.txt_ingredient_Quantity);
            txt_ingredient_measure = itemView.findViewById(R.id.txt_ingredient_measure);
            txt_ingredient_ingredient = itemView.findViewById(R.id.txt_ingredient_ingredient);
            iv_measure = itemView.findViewById(R.id.iv_measure);


        }
    }
}
