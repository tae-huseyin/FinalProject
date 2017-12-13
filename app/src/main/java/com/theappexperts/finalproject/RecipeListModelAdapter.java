package com.theappexperts.finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theappexperts.finalproject.data.network.model.RecipeListModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

public class RecipeListModelAdapter extends RecyclerView.Adapter<RecipeListModelAdapter.MyViewHolder> {

    private List<RecipeListModel> recipesList;
    private int row_list;
    private Context context;
    Unbinder unbinder;

    public RecipeListModelAdapter(List<RecipeListModel> recipesList, int row_list, Context context) {
        this.recipesList = recipesList;
        this.row_list = row_list;
        this.context = context;
    }

    @Override
    public RecipeListModelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, parent, false);
        unbinder = ButterKnife.bind(this, view);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListModelAdapter.MyViewHolder holder, int position) {
        //holder..setText(recipesList.get(position).getTitle())
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //@BindView()
        //TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
