package com.example.roboletricdemo.di;

import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.model.Repo;
import com.example.roboletricdemo.presenter.repository.RepositoryPresenter;
import com.example.roboletricdemo.view.repository.RepositoryListActivity;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.reactivex.internal.operators.flowable.FlowableError;
import io.reactivex.internal.operators.flowable.FlowableJust;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lap00168 on 9/9/17.
 */

@Module
public abstract class TesAppModule {

  @ContributesAndroidInjector
  abstract RepositoryListActivity contributeActivityInjector();

  @Provides
  static GithubService provideMockGithubService() {
    GithubService githubService = mock(GithubService.class);
    when(githubService.getRepositories()).thenReturn(new FlowableError<List<Repo>>(new
        FlowableJust<Throwable>(new Exception("Error"))));

    return githubService;
  }

  @Provides
  static RepositoryPresenter provideMockRepositoryPresenter(GithubService githubService) {
    return new RepositoryPresenter(githubService);
  }
}
