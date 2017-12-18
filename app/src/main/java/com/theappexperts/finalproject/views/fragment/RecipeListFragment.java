package com.theappexperts.finalproject.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theappexperts.finalproject.MyApp;
import com.theappexperts.finalproject.R;
import com.theappexperts.finalproject.data.network.consts.Constants;
import com.theappexperts.finalproject.data.network.model.Recipe;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.data.network.model.RecipeModel;
import com.theappexperts.finalproject.data.network.model.Recipes;
import com.theappexperts.finalproject.injection.components.ActivityComponent;
import com.theappexperts.finalproject.injection.components.DaggerActivityComponent;
import com.theappexperts.finalproject.injection.modules.ActivityModule;
import com.theappexperts.finalproject.views.recipelist.GetRecipeEvent;
import com.theappexperts.finalproject.views.recipelist.IRecipeListMvpView;
import com.theappexperts.finalproject.views.recipelist.PingRecipeEvent;
import com.theappexperts.finalproject.views.recipelist.RecipeListPresenter;
import com.theappexperts.finalproject.views.recipelist.SendNextPageEvent;
import com.theappexperts.finalproject.views.recipelist.SendRecipeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListFragment extends Fragment implements IRecipeListMvpView {

    //pagination stuff
    private int PAGE = 1;
    List<Recipe> savedList = new ArrayList<>();
    Recipes lastRecipeGotten;
    //end of

    //start of
    @Inject
    RecipeListPresenter<RecipeListFragment> recipeListPresenter;

    ActivityComponent activityComponent;

    @BindView(R.id.rvListOfRecipes)
    RecyclerView recyclerView;

    public void injectDagger()
    {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule((AppCompatActivity) getActivity()))
                .applicationComponent(((MyApp)getActivity().getApplicationContext()).getApplicationComponent())
                .build();

        activityComponent.inject(this);
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void setActivityComponent(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void initializePresenter()
    {
        recipeListPresenter.onAttach(this);
    }
    //end of








    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        injectDagger();
        ButterKnife.bind(this, view);
        initRecyclerView();
        initializePresenter();

        if(savedInstanceState == null){
            EventBus.getDefault().register(this);
        //first call to listener
            recipeListPresenter.onCallRecipeModelList(Constants.API_KEY, PAGE);

        }else{
            recyclerView.setAdapter(new RecipeListModelAdapter(savedList, getContext()));
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    PAGE += 1;
                    recipeListPresenter.onCallRecipeModelList(Constants.API_KEY, PAGE);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetRecipeEvent(GetRecipeEvent event) {
        //Toast.makeText(getActivity(), event.rId, Toast.LENGTH_SHORT).show();
        recipeListPresenter.onCallRecipeList(Constants.API_KEY, event.rId);
        showSnackbar(event.rId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPingRecipeEvent(PingRecipeEvent event) {
        EventBus.getDefault().post(new SendRecipeEvent(lastRecipeGotten));
    }

    private void showSnackbar(final String text) {
        View container = getView();
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }






    //startof presenter

    @Override
    public void onFetchDataSuccess(RecipeListModel recipeListModel) {
        //check if it is the first page then make a new adapter
        //or just send with EventBus the new data to load
        if(PAGE == 1) {
            recyclerView.setAdapter(new RecipeListModelAdapter(recipeListModel.getRecipes(), getContext()));
        }else{
            EventBus.getDefault().post(new SendNextPageEvent(recipeListModel.getRecipes()));
        }

        for(Recipe x : recipeListModel.getRecipes())
        {
            savedList.add(x);
        }
    }

    @Override
    public void onFetchDataSuccess(RecipeModel recipeModel) {

        lastRecipeGotten = recipeModel.getRecipes();

        FragmentManager fragManager = getFragmentManager();
        fragManager.beginTransaction()
                .add(R.id.frag_container, new RecipeViewFragment())
                .addToBackStack("RecipeWindow")
                .commit();
    }

    @Override
    public void onFetchDataError(String message) {
        if(PAGE == 1){
            showSnackbar(message);
        }else{
            showSnackbar(message);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
    //endof presenter
}
