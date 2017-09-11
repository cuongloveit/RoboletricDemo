package com.example.roboletricdemo;

import android.app.Application;
import com.example.roboletricdemo.di.AppComponent;
import com.example.roboletricdemo.di.DaggerAppComponent;

/**
 * Created by lap00168 on 9/8/17.
 */

public class RoboApplication extends Application {

  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    appComponent = getAppComponent();
  }

  public void setAppComponent(AppComponent appComponent) {
    this.appComponent = appComponent;
  }

  public AppComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = DaggerAppComponent.create();
    }
    return appComponent;
  }
}
