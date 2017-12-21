package com.theappexperts.finalproject.data.localdb.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by TheAppExperts on 19/12/2017.
 */

@Entity
public class RecipeList {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    /*@Embedded
    private recipeModel modelData;

    public recipeModel getModelData() {
        return modelData;
    }

    public void setModelData(recipeModel modelData) {
        this.modelData = modelData;
    }*/

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
