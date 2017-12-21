package com.theappexperts.finalproject.data.localdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.theappexperts.finalproject.data.localdb.daos.RecipeListDao;
import com.theappexperts.finalproject.data.localdb.entity.RecipeList;

/**
 * Created by TheAppExperts on 19/12/2017.
 */

@Database(entities = {RecipeList.class}, version = 1)
public abstract class RoomDB extends RoomDatabase{
    public abstract RecipeListDao recipeListDao();
}