package com.theappexperts.finalproject.views.recipelist;

import com.theappexperts.finalproject.data.network.model.Recipe;

import java.util.List;

/**
 * Created by TheAppExperts on 15/12/2017.
 */

public class SendNextPageEvent {

    public final List<Recipe> recipeList;

    public SendNextPageEvent(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

}
