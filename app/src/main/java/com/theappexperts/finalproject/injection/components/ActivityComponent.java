package com.theappexperts.finalproject.injection.components;

import com.theappexperts.finalproject.MainActivity;
import com.theappexperts.finalproject.injection.modules.ActivityModule;
import com.theappexperts.finalproject.injection.scope.PerActivity;

import dagger.Component;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
