package com.theappexperts.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.theappexperts.finalproject.network.RequestInterface;
import com.theappexperts.finalproject.network.consts.Constants;
import com.theappexperts.finalproject.network.pojo.RecipeListModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.theappexperts.finalproject.network.ConnectionService.getConnection;

public class MainActivity extends AppCompatActivity {

    private RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestInterface = getConnection();
        requestInterface.getPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RecipeListModel>() {
                    @Override
                    public void accept(RecipeListModel recipeListModel) throws Exception {
                        Log.i("data", "getting data!");
                    }
                });

    }
}
