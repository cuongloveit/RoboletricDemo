package com.example.roboletricdemo.view.repository;

import com.example.roboletricdemo.model.Repo;
import com.example.roboletricdemo.view.MvpView;
import java.util.List;

/**
 * Created by lap00168 on 9/8/17.
 */

public interface ListRepositoryView extends MvpView {
  void onGetListRepositorySuccess(List<Repo> repos);

  void onGetListRepositoryError(String error);
}
