package com.theappexperts.finalproject.injection.modules;

import android.app.Application;
import android.content.Context;

import com.theappexperts.finalproject.data.AppDataManager;
import com.theappexperts.finalproject.data.IDataManager;
import com.theappexperts.finalproject.data.network.ApiHelper;
import com.theappexperts.finalproject.data.network.AppApiHelper;
import com.theappexperts.finalproject.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context getContext()
    {
        return application;
    }

    @Provides
    Application getApplication()
    {
        return  application;
    }

    @Provides
    @Singleton
    IDataManager getDataManager(AppDataManager appDataManager)
    {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper getApiHelper(AppApiHelper appApiHelper)
    {
        return appApiHelper;
    }

}
