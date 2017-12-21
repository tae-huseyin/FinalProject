package com.theappexperts.finalproject.data.localdb.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.theappexperts.finalproject.data.localdb.entity.RecipeList;

import java.util.List;

/**
 * Created by TheAppExperts on 19/12/2017.
 */

@Dao
public interface RecipeListDao {

    @Query("SELECT * FROM recipelist")
    List<RecipeList> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecipeList recipeLists);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RecipeList> recipeLists);

    @Update
    void update(RecipeList recipeList);

    @Delete
    void delete(RecipeList recipeList);

    @Query("DELETE FROM recipelist")
    public void nukeTable();
}
