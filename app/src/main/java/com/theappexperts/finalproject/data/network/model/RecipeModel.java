package com.theappexperts.finalproject.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeModel {

    @SerializedName("recipe")
    @Expose
    private Recipes recipes;

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipe(Recipes recipes) {
        this.recipes = recipes;
    }

}
