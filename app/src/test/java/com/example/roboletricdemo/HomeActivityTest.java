package com.example.roboletricdemo;

import android.widget.TextView;
import com.example.roboletricdemo.base.BaseUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by lap00168 on 9/7/17.
 */

public class HomeActivityTest extends BaseUnitTest {

  private HomeActivity activity;

  @Before
  public void setup(){
    activity = Robolectric.buildActivity(HomeActivity.class).create().get();
  }

  @Test
  public void testLifeCycle() throws Exception{
    ActivityController controller = Robolectric.buildActivity(HomeActivity.class).create().start();
    HomeActivity activity = (HomeActivity) controller.get();
    assertNotNull(activity);
    controller.resume();

  }
}
