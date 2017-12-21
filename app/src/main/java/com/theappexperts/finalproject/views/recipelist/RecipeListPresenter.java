package com.theappexperts.finalproject.views.recipelist;

import android.util.Log;

import com.theappexperts.finalproject.MyApp;
import com.theappexperts.finalproject.data.IDataManager;
import com.theappexperts.finalproject.data.localdb.entity.RecipeList;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.data.network.model.RecipeModel;
import com.theappexperts.finalproject.views.ui.base.BasePresenter;
import com.theappexperts.finalproject.views.ui.utils.rx.SchedulerProvider;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

public class RecipeListPresenter<V extends IRecipeListMvpView>
        extends BasePresenter<V>
        implements IRecipeListMvpPresenter<V> {

    @Inject
    public RecipeListPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onCallRecipeModelList(String key) {

        getCompositeDisposable().add(
                getDataManager().getFromApi_RecipeList(key)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<RecipeListModel>() {
                               @Override
                               public void accept(RecipeListModel recipeListModel) throws Exception {
                                   getMvpView().onFetchDataSuccess(recipeListModel);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().onFetchDataError(throwable.getMessage());
                            }
                        })
        );

    }

    @Override
    public void onCallRecipeModelList(String key, int page) {

            getCompositeDisposable().add(
                    getDataManager().getFromApi_RecipeList(key, page)
                            .observeOn(getSchedulerProvider().ui())
                            .subscribeOn(getSchedulerProvider().io())
                            .subscribe(new Consumer<RecipeListModel>() {
                                           @Override
                                           public void accept(RecipeListModel recipeListModel) throws Exception {
                                               MyApp.get().saveTimeDate();
                                               getMvpView().onFetchDataSuccess(recipeListModel);
                                           }
                                       },
                                    new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            getMvpView().onFetchDataError(throwable.getMessage());
                                        }
                                    })
            );
        if(MyApp.get().getMin() < Calendar.getInstance().get(Calendar.MINUTE) - 1) {
            Log.i("test", "gotten= " + MyApp.get().getMin() + " now = " + Calendar.getInstance().get(Calendar.MINUTE));
        }else{
            Log.i("test", "cache// gotten= " + MyApp.get().getMin() + " now = " + Calendar.getInstance().get(Calendar.MINUTE));
        }
    }

    @Override
    public void onCallRecipeList(String key, String rId) {
        getCompositeDisposable().add(
                getDataManager().getFromApi_Recipe(key, rId)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<RecipeModel>() {
                               @Override
                               public void accept(RecipeModel recipeModel) throws Exception {
                                   getMvpView().onFetchDataSuccess(recipeModel);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().onFetchDataError(throwable.getMessage());
                            }
                        })
        );
    }

}
