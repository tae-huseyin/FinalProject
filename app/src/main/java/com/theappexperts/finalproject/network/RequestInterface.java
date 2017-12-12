package com.theappexperts.finalproject.network;

import com.theappexperts.finalproject.network.consts.Constants;
import com.theappexperts.finalproject.network.pojo.RecipeListModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TheAppExperts on 12/12/2017.
 */

public interface RequestInterface {

    @GET("search?key=a8bb11868359575a5a0c623aea4bf166")
    Observable<RecipeListModel> getPopular();

}
