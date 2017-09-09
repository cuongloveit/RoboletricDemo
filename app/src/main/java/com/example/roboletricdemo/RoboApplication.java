package com.example.roboletricdemo;

import android.app.Activity;
import android.app.Application;
import com.example.roboletricdemo.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

/**
 * Created by lap00168 on 9/8/17.
 */

public class RoboApplication extends Application implements HasActivityInjector {
  @Override public void onCreate() {
    super.onCreate();
    appComponentInject();
  }

  public void appComponentInject() {
    DaggerAppComponent.create().inject(this);
  }

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}
