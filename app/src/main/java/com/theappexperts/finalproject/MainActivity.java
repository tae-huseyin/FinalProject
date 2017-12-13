package com.theappexperts.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.injection.components.ActivityComponent;
import com.theappexperts.finalproject.injection.components.DaggerActivityComponent;
import com.theappexperts.finalproject.injection.modules.ActivityModule;
import com.theappexperts.finalproject.views.recipelist.IRecipeListMvpView;
import com.theappexperts.finalproject.views.recipelist.RecipeListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IRecipeListMvpView{

    @Inject
    RecipeListPresenter<MainActivity> recipeListPresenter;

    ActivityComponent activityComponent;
    @BindView(R.id.rvRecipeList)
    RecyclerView recyclerView;

    public void injectDagger()
    {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApp)getApplicationContext()).getApplicationComponent())
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void initializePresenter()
    {
        recipeListPresenter.onAttach(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDagger();
        ButterKnife.bind(this);
        initRecyclerView();
        initializePresenter();

        recipeListPresenter.onCallRecipeModelList();
    }

    @Override
    public void onFetchDataSuccess(RecipeListModel recipeListModel) {
        recyclerView.setAdapter(new RecipeListModelAdapter(recipeListModel.getRecipes(), getApplicationContext()));
    }

    @Override
    public void onFetchDataError(String message) {
        Log.i("data", "nodata");
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
}
