package com.theappexperts.finalproject.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theappexperts.finalproject.MyApp;
import com.theappexperts.finalproject.R;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.injection.components.ActivityComponent;
import com.theappexperts.finalproject.injection.components.DaggerActivityComponent;
import com.theappexperts.finalproject.injection.modules.ActivityModule;
import com.theappexperts.finalproject.views.recipelist.GetRecipeEvent;
import com.theappexperts.finalproject.views.recipelist.IRecipeListMvpView;
import com.theappexperts.finalproject.views.recipelist.RecipeListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListFragment extends Fragment implements IRecipeListMvpView {

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

        injectDagger();
        ButterKnife.bind(this, view);
        initRecyclerView();
        initializePresenter();

        EventBus.getDefault().register(this);
        recipeListPresenter.onCallRecipeModelList();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetRecipeEvent(GetRecipeEvent event) {
        Toast.makeText(getActivity(), event.tag, Toast.LENGTH_SHORT).show();
    }













    //startof presenter
    @Override
    public void onFetchDataSuccess(RecipeListModel recipeListModel) {
        recyclerView.setAdapter(new RecipeListModelAdapter(recipeListModel.getRecipes(), getContext()));
    }

    @Override
    public void onFetchDataError(String message) {

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
