package com.theappexperts.finalproject.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.theappexperts.finalproject.R;
import com.theappexperts.finalproject.data.network.model.Recipe;
import com.theappexperts.finalproject.views.recipelist.GetRecipeEvent;
import com.theappexperts.finalproject.views.recipelist.SendNextPageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSendNextPageEvent(SendNextPageEvent event) {

        int getCurSize = recipesList.size();
        for (Recipe x : event.recipeList) {
            recipesList.add(x);
        }
        notifyItemRangeChanged(getCurSize,recipesList.size());
    }

    @Override
    public RecipeListModelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipes_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListModelAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(recipesList.get(position).getTitle());
        holder.tvPublisher.setText(recipesList.get(position).getPublisher());
        holder.ivFood.setImageURI(Uri.parse(recipesList.get(position).getImageUrl()));
        holder.btnGetRecipes.setTag(position);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Unbinder unbinder;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvPublisher)
        TextView tvPublisher;

        @BindView(R.id.ivFood)
        SimpleDraweeView ivFood;

        @BindView(R.id.btn_GetRecipes)
        Button btnGetRecipes;

        @OnClick(R.id.btn_GetRecipes)
        public void getRecipe(Button button) {


            /*AsyncExecutor.create().execute(
                    new AsyncExecutor.RunnableEx() {
                        @Override
                        public void run() throws Exception {
                            // No need to catch any Exception (here: LoginException)
                            EventBus.getDefault().postSticky(new GetRecipeEvent("" + recipesList.size()));
                        }
                    }
            );*/

            EventBus.getDefault().post(new GetRecipeEvent(""+recipesList.size()));
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
