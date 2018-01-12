package com.example.common;

import android.app.Application;

/**
 * Created by lap00168 on 9/8/17.
 */

public class TestApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    setTheme(R.style.Theme_AppCompat_NoActionBar);
  }

}
