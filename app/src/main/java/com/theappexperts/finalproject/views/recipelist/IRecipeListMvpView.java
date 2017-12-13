package com.theappexperts.finalproject.views.recipelist;

import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.views.ui.base.MvpView;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

public interface IRecipeListMvpView extends MvpView{

    void onFetchDataSuccess(RecipeListModel recipeListModel);
    void onFetchDataError(String message);

}
