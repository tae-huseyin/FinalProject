package com.theappexperts.finalproject.views.recipelist;

import com.theappexperts.finalproject.views.ui.base.MvpPresenter;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

public interface IRecipeListMvpPresenter<V extends IRecipeListMvpView>
    extends MvpPresenter<V>
{
    void onCallRecipeModelList();
}
