package com.theappexperts.finalproject;

import android.app.Application;

import com.theappexperts.finalproject.injection.components.ApplicationComponent;
import com.theappexperts.finalproject.injection.components.DaggerApplicationComponent;
import com.theappexperts.finalproject.injection.modules.ApplicationModule;


/**
 * Created by TheAppExperts on 13/12/2017.
 */

public class MyApp extends Application {
    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {return applicationComponent; }

    public void setApplicationComponent(ApplicationComponent applicationComponent){
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
