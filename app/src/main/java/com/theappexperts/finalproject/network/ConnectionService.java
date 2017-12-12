package com.theappexperts.finalproject.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.theappexperts.finalproject.network.consts.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TheAppExperts on 12/12/2017.
 */

public class ConnectionService {

    static Retrofit retrofit;

    public static RequestInterface getConnection(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(RequestInterface.class);
    }

}
