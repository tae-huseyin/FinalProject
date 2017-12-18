package com.theappexperts.finalproject.views.recipelist;

import com.theappexperts.finalproject.data.network.model.Recipe;
import com.theappexperts.finalproject.data.network.model.Recipes;

import java.util.List;

/**
 * Created by TheAppExperts on 18/12/2017.
 */

public class SendRecipeEvent {

    public final Recipes recipe;

    public SendRecipeEvent(Recipes recipe) {
        this.recipe = recipe;
    }

}
