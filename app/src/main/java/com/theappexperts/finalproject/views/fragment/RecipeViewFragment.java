package com.theappexperts.finalproject.views.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theappexperts.finalproject.R;
import com.theappexperts.finalproject.data.network.model.Recipes;
import com.theappexperts.finalproject.views.recipelist.PingRecipeEvent;
import com.theappexperts.finalproject.views.recipelist.SendRecipeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeViewFragment extends Fragment {

    Recipes recipe;

    Unbinder unbinder;

    @BindView(R.id.ivRecipePicture)
    ImageView ivRecipePicture;

    @BindView(R.id.tvRecipeTitle)
    TextView tvRecipeTitle;

    @BindView(R.id.tvRecipeList)
    TextView tvRecipeList;

    public RecipeViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        EventBus.getDefault().post(new PingRecipeEvent());
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetRecipeEvent(SendRecipeEvent event) {
        recipe = event.recipe;
        putRecipeOnView();
    }

    void putRecipeOnView()
    {
        ivRecipePicture.setImageURI(Uri.parse(recipe.getImageUrl()));
        tvRecipeTitle.setText(recipe.getTitle());
        //make forloop
        tvRecipeList.setText(recipe.getIngredients().get(0).toString());
    }
}
