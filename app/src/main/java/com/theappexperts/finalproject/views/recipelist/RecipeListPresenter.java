package com.theappexperts.finalproject.views.recipelist;

import com.theappexperts.finalproject.data.IDataManager;
import com.theappexperts.finalproject.data.network.consts.Constants;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.views.ui.base.BasePresenter;
import com.theappexperts.finalproject.views.ui.utils.rx.SchedulerProvider;

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

}
