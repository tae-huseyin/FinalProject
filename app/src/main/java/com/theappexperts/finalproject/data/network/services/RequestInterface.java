package com.theappexperts.finalproject.data.network.services;

import com.theappexperts.finalproject.data.network.consts.Constants;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TheAppExperts on 12/12/2017.
 */

public interface RequestInterface {

    @GET(Constants.SEARCH)
    Observable<RecipeListModel> getPopular(@Query("key") String key);

    @GET(Constants.SEARCH)
    Observable<RecipeListModel> getPopular(@Query("key") String key, @Query("page") int page);

    @GET(Constants.RECIPE)
    Observable<RecipeListModel> getRecipe(@Query("key") String key, @Query("rId") String rId);

}
