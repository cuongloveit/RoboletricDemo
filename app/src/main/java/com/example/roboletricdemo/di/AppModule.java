package com.example.roboletricdemo.di;

import com.example.roboletricdemo.api.APIHelper;
import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.view.repository.RepositoryListActivity;
import com.example.roboletricdemo.view.ui.MainActivity;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {
  @ContributesAndroidInjector
  public abstract RepositoryListActivity contributeActivityInjector();

  @Provides
  static GithubService provideGithubService() {
    return APIHelper.getApi();
  }
}