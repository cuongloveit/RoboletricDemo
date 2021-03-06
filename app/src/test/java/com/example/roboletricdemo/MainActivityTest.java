package com.example.roboletricdemo;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import com.example.roboletricdemo.base.BaseUnitTest;
import com.example.roboletricdemo.view.ui.HomeActivity;
import com.example.roboletricdemo.view.ui.MainActivity;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.robolectric.Shadows.shadowOf;


public class MainActivityTest extends BaseUnitTest {

  private Activity activity;

  @Before
  public void setup() {
    activity = Robolectric.buildActivity(MainActivity.class).create().get();
  }

  @Test
  public void validateButtonClick() throws Exception{
    Button button = activity.findViewById(R.id.button_simple);

    button.performClick();
    ShadowActivity shadowActivity = shadowOf(activity);
    Intent startedIntent = shadowActivity.getNextStartedActivity();
    ShadowIntent shadowIntent = shadowOf(startedIntent);
    assertThat(shadowIntent.getIntentClass().getName(), equalTo(HomeActivity.class.getName()));
  }

  @Test
  public void testLifeCycle() throws Exception{
    ActivityController controller = Robolectric.buildActivity(MainActivity.class).create().start();
    MainActivity activity = (MainActivity) controller.get();
    assertNotNull(activity);

    TextView textView = (TextView) activity.findViewById(R.id.text_view);
    assertEquals(textView.getText().toString(), "loading");
    controller.resume();
    assertEquals(textView.getText().toString(), "complete");
  }

  @Test
  @Config(qualifiers = "en")
  public void testEnResource() throws Exception {
    String message = activity.getString(R.string.message);
    assertEquals(message, "Hello");
  }

  @Test
  @Config(qualifiers = "vi")
  public void testViResource() throws Exception{
    String message = activity.getString(R.string.message);
    assertEquals(message, "Xin chào");
  }

  @Test
  public void validateTextViewContent() throws Exception {
    TextView textView = activity.findViewById(R.id.text_view);
    assertNotNull("TextView is null", textView);
  }

}