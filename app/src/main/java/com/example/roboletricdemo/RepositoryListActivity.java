package com.example.roboletricdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.roboletricdemo.api.APIHelper;
import com.example.roboletricdemo.model.Repo;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by lap00168 on 9/3/17.
 */

public class RepositoryListActivity extends AppCompatActivity {
  private RecyclerView rcRepos;
  private ProgressBar pbLoading;
  private TextView tvMessage;
  private RepositoryAdapter adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repository_list);
    rcRepos = (RecyclerView) findViewById(R.id.rcRepo);
    pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
    tvMessage = (TextView) findViewById(R.id.tvMessage);

    requestRepositories();
  }

  private void requestRepositories() {
    pbLoading.setVisibility(View.VISIBLE);
    APIHelper.getApi().getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Repo>>() {
          @Override public void accept(
              @NonNull List<Repo> repos) throws Exception {
            pbLoading.setVisibility(View.GONE);
            tvMessage.setVisibility(View.GONE);
            showListRepository(repos);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(@NonNull Throwable throwable) throws Exception {
            pbLoading.setVisibility(View.GONE);
            tvMessage.setText("Please try again!");
            tvMessage.setVisibility(View.VISIBLE);
          }
        });
  }

  public void showListRepository(List<Repo> repos) {
    adapter = new RepositoryAdapter(repos);
    rcRepos.setLayoutManager(new LinearLayoutManager(this));
    rcRepos.setAdapter(adapter);
  }
}
