package com.theappexperts.finalproject.views.fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

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
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecipeViewFragment extends Fragment {

    Recipes recipe;

    Unbinder unbinder;

    @BindView(R.id.ivRecipePicture)
    ImageView ivRecipePicture;

    @BindView(R.id.tbRecipeTitle)
    Toolbar tbRecipeTitle;

    @BindView(R.id.tvRecipeList)
    TextView tvRecipeList;

    @OnClick(R.id.fbtnWebsite)
    public void goToSite(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getPublisherUrl()));
        startActivity(intent);
    }

    @OnClick(R.id.fbtnMap)
    public void goToMap(){
        FragmentManager fragManager = getFragmentManager();
        fragManager.beginTransaction()
                .add(R.id.frag_container, new MapFragment())
                .addToBackStack("MapWindow")
                .commit();
    }

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
        setRetainInstance(true);

        if(savedInstanceState == null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(tbRecipeTitle);
        }

        EventBus.getDefault().register(this);

        EventBus.getDefault().post(new PingRecipeEvent());//thing that the fragment is ready
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
        putRecipeOnView();//get from ping the data and put on view
    }

    void putRecipeOnView()
    {
        ivRecipePicture.setImageURI(Uri.parse(recipe.getImageUrl()));
        tbRecipeTitle.setTitle(recipe.getTitle());

        tvRecipeList.append("\n\n");
        for(String x: recipe.getIngredients())
        {
            tvRecipeList.append("- " + x + "\n\n");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        tbRecipeTitle.setTitle(recipe.getTitle());
    }
}
