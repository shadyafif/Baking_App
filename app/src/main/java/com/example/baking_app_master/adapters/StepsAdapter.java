package com.example.baking_app_master.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.baking_app_master.R;
import com.example.baking_app_master.activities.RecipeDetailsActivity;
import com.example.baking_app_master.activities.StepsDetailsActivity;
import com.example.baking_app_master.fragments.StepDetailsFragment;
import com.example.baking_app_master.model.RecipesData.Recipe;
import com.example.baking_app_master.model.RecipesData.Step;

import java.util.List;

import static com.example.baking_app_master.adapters.Helper.Replace;


public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    Context context;
    List<Step> StepList;
    Recipe recipe;


    public StepsAdapter(Context context, List<Step> stepList) {
        this.context = context;
        StepList = stepList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.stepslayout, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int poisiton) {
        final Step step = StepList.get(poisiton);
        viewHolder.txtStepName.setText(step.getShortDescription());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet(context)) {
                    RecipeDetailsActivity recipeDetailsActivity = (RecipeDetailsActivity) context;
                    StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Step", step);
                    stepDetailsFragment.setArguments(bundle);
                    Replace(stepDetailsFragment, R.id.detailContainer, recipeDetailsActivity.getSupportFragmentManager().beginTransaction());

                } else {

                    Intent intent = new Intent(context, StepsDetailsActivity.class);
                    intent.putExtra("Step", step);
                    intent.putExtra("Recipe", recipe);
                    context.startActivity(intent);
                }


            }


        });


    }


    @Override
    public int getItemCount() {
        return StepList != null ? StepList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStepName;
        ImageView iv_Step_Play;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStepName = itemView.findViewById(R.id.txtStepName);
            iv_Step_Play =  itemView.findViewById(R.id.iv_Step_Play);
        }
    }

    private boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }


}
