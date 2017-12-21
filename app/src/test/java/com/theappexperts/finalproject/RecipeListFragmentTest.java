package com.theappexperts.finalproject;

import com.theappexperts.finalproject.data.AppDataManager;
import com.theappexperts.finalproject.data.IDataManager;
import com.theappexperts.finalproject.data.network.ApiHelper;
import com.theappexperts.finalproject.data.network.consts.Constants;
import com.theappexperts.finalproject.data.network.model.Recipe;
import com.theappexperts.finalproject.data.network.model.RecipeListModel;
import com.theappexperts.finalproject.views.recipelist.IRecipeListMvpView;
import com.theappexperts.finalproject.views.recipelist.RecipeListPresenter;
import com.theappexperts.finalproject.views.ui.utils.rx.SchedulerProvider;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TheAppExperts on 21/12/2017.
 */

public class RecipeListFragmentTest {

    @Mock
    IRecipeListMvpView mockView;

    @Mock
    RecipeListPresenter presenter;

    @Mock
    AppDataManager dataManager;

    @Mock
    SchedulerProvider schedulerProvider;

    @Mock
    CompositeDisposable compositeDisposable;

    @Mock
    RecipeListModel response;

    @Mock
    List<Recipe> recipes;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(IRecipeListMvpView.class);

        presenter = new RecipeListPresenter(dataManager, schedulerProvider, compositeDisposable);



        response = new RecipeListModel();

        Recipe e = new Recipe();
        e.setF2fUrl("");
        e.setImageUrl("");
        e.setPublisher("");
        e.setPublisherUrl("");
        e.setRecipeId("");
        e.setSocialRank(0.0);
        e.setSourceUrl("");
        e.setTitle("");
        recipes = new ArrayList<>();
        recipes.add(e);

        response.setRecipes(recipes);
    }

    /*@Test
    public void testDataShouldLoad() {

        presenter.onAttach(mockView);
         when(dataManager.getFromApi_RecipeList(Constants.API_KEY)).thenReturn(Observable.just(response));

    }*/

}
