package com.example.roboletricdemo.di;

import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.presenter.repository.RepositoryPresenter;
import com.example.roboletricdemo.view.repository.RepositoryListActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {
  void inject(RepositoryListActivity repositoryListActivity);
  GithubService gethubService();
  RepositoryPresenter getRepository();
}