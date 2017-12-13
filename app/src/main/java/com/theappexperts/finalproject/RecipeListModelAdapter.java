package com.theappexperts.finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.theappexperts.finalproject.data.network.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

public class RecipeListModelAdapter extends RecyclerView.Adapter<RecipeListModelAdapter.MyViewHolder> {

    private List<Recipe> recipesList;
    private Context context;

    public RecipeListModelAdapter(List<Recipe> recipesList, Context context) {
        this.recipesList = recipesList;
        this.context = context;
    }

    @Override
    public RecipeListModelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipes_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListModelAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(recipesList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Unbinder unbinder;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        //@BindView(R.id.ivFood)
        //ImageView ivFood;

        public MyViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
