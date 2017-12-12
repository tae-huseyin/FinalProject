package com.theappexperts.finalproject.network.pojo;

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
