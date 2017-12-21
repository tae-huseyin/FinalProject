package com.theappexperts.finalproject;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.theappexperts.finalproject.data.localdb.RoomDB;
import com.theappexperts.finalproject.injection.components.ApplicationComponent;
import com.theappexperts.finalproject.injection.components.DaggerApplicationComponent;
import com.theappexperts.finalproject.injection.modules.ApplicationModule;

import java.util.Calendar;


/**
 * Created by TheAppExperts on 13/12/2017.
 */

public class MyApp extends Application {

    public static MyApp INSTANCE;

    ApplicationComponent applicationComponent;

    private SharedPreferences pref;
    SharedPreferences.Editor editor;
    private RoomDB db;

    public static MyApp get() {
        return INSTANCE;
    }

    public RoomDB DB() {
        return db;
    }

    //for get and setting time date to check when to user cached data
    public void saveTimeDate()
    {
        editor.putInt("DAY", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        editor.putInt("MONTH", Calendar.getInstance().get(Calendar.MONTH));
        editor.putInt("YEAR", Calendar.getInstance().get(Calendar.YEAR));
        editor.putInt("HOUR", Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        editor.putInt("MIN", Calendar.getInstance().get(Calendar.MINUTE));
        editor.commit();
    }

    public int getDay(){return pref.getInt("DAY", 0);}
    public int getMonth(){return pref.getInt("MONTH", 0);}
    public int getYear(){return pref.getInt("YEAR", 0);}
    public int getHour(){return pref.getInt("HOUR", 0);}
    public int getMin(){return pref.getInt("MIN", 0);}
    //end of

    public ApplicationComponent getApplicationComponent() {return applicationComponent; }

    public void setApplicationComponent(ApplicationComponent applicationComponent){
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        pref = getApplicationContext().getSharedPreferences("storedTimes", MODE_PRIVATE);
        editor = pref.edit();

        db = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "recipeList").build();

        INSTANCE = this;
    }
}
