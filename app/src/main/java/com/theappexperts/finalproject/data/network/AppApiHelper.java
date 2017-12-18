package com.theappexperts.finalproject.data.network;

import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.data.network.model.RecipeModel;
import com.theappexperts.finalproject.data.network.services.RequestInterface;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 12/12/2017.
 */

@Singleton
public class AppApiHelper implements ApiHelper{
    private RequestInterface requestInterface;

    @Inject
    public AppApiHelper(RequestInterface requestInterface){
        this.requestInterface = requestInterface;
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key) {
        return requestInterface.getPopular(key);
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key, int page) {
        return requestInterface.getPopular(key, page);
    }

    @Override
    public Observable<RecipeModel> getFromApi_Recipe(String key, String rId) {
        return requestInterface.getRecipe(key ,rId);
    }
}
