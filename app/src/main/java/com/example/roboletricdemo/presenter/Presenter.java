package com.example.roboletricdemo.presenter;

import com.example.roboletricdemo.view.MvpView;

/**
 * Created by lap00168 on 9/8/17.
 */

public class Presenter<V extends MvpView> {

  protected V view;

  public void attachView(MvpView view) {
    this.view = (V) view;
  }

  public void dettachView() {
    view = null;
  }

  public V getView() {
    return view;
  }
}
