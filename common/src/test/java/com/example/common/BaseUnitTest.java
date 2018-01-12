package com.example.common;

import android.os.Build;
import java.util.List;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by lap00168 on 8/29/17.
 */

@Config(constants = BuildConfig.class,
    sdk = Build.VERSION_CODES.LOLLIPOP_MR1,
    application = TestApplication.class)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseUnitTest {

}
