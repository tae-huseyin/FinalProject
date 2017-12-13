package com.theappexperts.finalproject.injection.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.theappexperts.finalproject.injection.scope.ActivityContext;
import com.theappexperts.finalproject.views.ui.utils.rx.AppSchedulerProvider;
import com.theappexperts.finalproject.views.ui.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

@Module
public class ActivityModule {

    AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context getActivityContext()
    {
        return appCompatActivity;
    }

    @Provides
    AppCompatActivity getAppCompatActivity()
    {
        return appCompatActivity;
    }

    @Provides
    SchedulerProvider appSchedulerProvider()
    {
        return new AppSchedulerProvider();
    }

    @Provides
    CompositeDisposable getCompositeDisposable()
    {
        return new CompositeDisposable();
    }

    //TODO do listmvppresenter
}
