package com.example.roboletricdemo.di;

import com.example.roboletricdemo.RoboApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = { AndroidInjectionModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<RoboApplication> {
}