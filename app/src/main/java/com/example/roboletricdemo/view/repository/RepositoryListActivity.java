package com.example.roboletricdemo.view.repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.roboletricdemo.R;
import com.example.roboletricdemo.RoboApplication;
import com.example.roboletricdemo.model.Repo;
import com.example.roboletricdemo.model.RepoSearchResponse;
import com.example.roboletricdemo.presenter.repository.RepositoryPresenter;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by lap00168 on 9/3/17.
 */

public class RepositoryListActivity extends AppCompatActivity implements ListRepositoryView {
  private RecyclerView rcRepos;
  private ProgressBar pbLoading;
  private TextView tvMessage;
  private RepositoryAdapter adapter;

  @Inject RepositoryPresenter presenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    ((RoboApplication) getApplication()).getAppComponent().inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repository_list);
    rcRepos = (RecyclerView) findViewById(R.id.rcRepo);
    pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
    tvMessage = (TextView) findViewById(R.id.tvMessage);

    presenter.attachView(this);
    pbLoading.setVisibility(View.VISIBLE);
    presenter.requestRepositories();
  }

  @Override public void onGetListRepositorySuccess(List<Repo> repos) {
    pbLoading.setVisibility(View.GONE);
    tvMessage.setVisibility(View.GONE);
    rcRepos.setVisibility(View.VISIBLE);
    adapter = new RepositoryAdapter(repos);
    rcRepos.setLayoutManager(new LinearLayoutManager(this));
    rcRepos.setAdapter(adapter);
  }

  @Override public void onGetListRepositoryError(String error) {
    pbLoading.setVisibility(View.GONE);
    rcRepos.setVisibility(View.GONE);
    tvMessage.setText(error);
    tvMessage.setVisibility(View.VISIBLE);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.dettachView();
  }
}
