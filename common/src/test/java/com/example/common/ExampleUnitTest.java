package com.example.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@Config(constants = BuildConfig.class)
public class ExampleUnitTest extends BaseUnitTest {
  @Test
  public void addition_isCorrect() throws Exception {
    CommonActivity commonActivity = Robolectric.buildActivity(CommonActivity.class).create().get();
    assertNotNull(commonActivity);
  }
}