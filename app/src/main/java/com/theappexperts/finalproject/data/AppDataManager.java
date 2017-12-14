package com.theappexperts.finalproject.data;

import android.content.Context;

import com.theappexperts.finalproject.data.network.ApiHelper;
import com.theappexperts.finalproject.data.network.AppApiHelper;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.injection.scope.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 13/12/2017.
 */
@Singleton
public class AppDataManager implements IDataManager {

    private ApiHelper apiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context application, AppApiHelper appApiHelper){
        this.apiHelper = appApiHelper;
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key) {
        return apiHelper.getFromApi_RecipeList(key);
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key, int page) {
        return apiHelper.getFromApi_RecipeList(key, page);
    }
    //TODO here

}
