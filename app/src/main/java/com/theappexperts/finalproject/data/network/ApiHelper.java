package com.theappexperts.finalproject.data.network;

import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.data.network.model.RecipeModel;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 12/12/2017.
 */

public interface ApiHelper {
    Observable<RecipeListModel> getFromApi_RecipeList(String key);

    Observable<RecipeListModel> getFromApi_RecipeList(String key, int page);

    //get the clicked recipe
    Observable<RecipeModel> getFromApi_Recipe(String key, String rId);
}
