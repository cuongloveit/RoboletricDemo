package com.example.roboletricdemo.base;

import com.example.roboletricdemo.RoboApplication;
import com.example.roboletricdemo.di.DaggerTestAppComponent;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lap00168 on 9/8/17.
 */

public class TestApplication extends RoboApplication {
  @Override
  public void onCreate() {
    setupRxSchedulers();
    super.onCreate();
  }

  @Override public void appComponentInject() {
    DaggerTestAppComponent.create().inject(this);
  }

  private void setupRxSchedulers() {
    RxJavaPlugins.reset();
    RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
      @Override public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
        return Schedulers.trampoline();
      }
    });
    RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
      @Override public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
        return Schedulers.trampoline();
      }
    });
  }
}
