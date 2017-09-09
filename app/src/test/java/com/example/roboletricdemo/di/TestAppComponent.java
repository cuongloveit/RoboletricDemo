package com.example.roboletricdemo.di;

import com.example.roboletricdemo.RepositoryListActivityTest;
import com.example.roboletricdemo.base.TestApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by lap00168 on 9/9/17.
 */
@Component(modules = {AndroidInjectionModule.class,TesAppModule.class})
public interface TestAppComponent extends AndroidInjector<TestApplication>{

  void inject(RepositoryListActivityTest repositoryListActivityTest);
}
