package com.example.roboletricdemo.base;

import android.os.Build;
import com.example.roboletricdemo.BuildConfig;
import io.reactivex.subscribers.TestSubscriber;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

/**
 * Created by lap00168 on 8/29/17.
 */

@Config(constants = BuildConfig.class, sdk = Build
    .VERSION_CODES.LOLLIPOP_MR1)
@RunWith(CustomRobolectricRunner.class)
public abstract class BaseUnitTest {

  public <D> D getData(TestSubscriber<D> testSubscriber) {
    List<D> dataEvents = (List<D>) testSubscriber.getEvents().get(0);
    return dataEvents.get(0);
  }

}
