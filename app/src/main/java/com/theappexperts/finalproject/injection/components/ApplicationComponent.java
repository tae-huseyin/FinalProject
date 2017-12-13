package com.theappexperts.finalproject.injection.components;

import android.app.Application;
import android.content.Context;

import com.theappexperts.finalproject.MyApp;
import com.theappexperts.finalproject.data.IDataManager;
import com.theappexperts.finalproject.data.network.ApiHelper;
import com.theappexperts.finalproject.injection.modules.ApplicationModule;
import com.theappexperts.finalproject.injection.modules.NetworkModule;
import com.theappexperts.finalproject.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(MyApp myApp);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();

    ApiHelper getApiHelper();
}
