package com.example.roboletricdemo.presenter.repository;

import android.util.Log;
import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.model.Repo;
import com.example.roboletricdemo.presenter.Presenter;
import com.example.roboletricdemo.view.repository.ListRepositoryView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by lap00168 on 9/8/17.
 */

public class RepositoryPresenter extends Presenter<ListRepositoryView> {

  private final GithubService githubService;

  public RepositoryPresenter(GithubService githubService) {
    this.githubService = githubService;
  }

  public void requestRepositories() {
    Flowable<List<Repo>> call = githubService.getRepositories();
    call.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Repo>>() {
          @Override public void accept(
              @NonNull List<Repo> repos) throws Exception {
            if (getView() != null) {
              getView().onGetListRepositorySuccess(repos);
            }
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(@NonNull Throwable throwable) throws Exception {
            if (getView() != null) {
              getView().onGetListRepositoryError(throwable.getMessage());
            }
          }
        });
  }

  public String test() {
    return "test";
  }
}
