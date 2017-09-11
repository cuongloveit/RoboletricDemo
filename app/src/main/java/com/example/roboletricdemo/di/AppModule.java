package com.example.roboletricdemo.di;

import com.example.roboletricdemo.api.APIHelper;
import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.model.RepoSearchResponse;
import com.example.roboletricdemo.presenter.repository.RepositoryPresenter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  @Provides @Singleton public GithubService provideGithubService() {
    return APIHelper.getApi();
  }

  @Provides @Singleton public RepositoryPresenter provideRepostoryPresenter(
      GithubService githubService) {
    return new RepositoryPresenter(githubService);
  }
}