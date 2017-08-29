package com.example.roboletricdemo.base;

import android.os.Build;
import com.example.roboletricdemo.BuildConfig;
import com.example.roboletricdemo.R;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

public class CustomRobolectricRunner extends RobolectricTestRunner

{
    // Build output location for Android Studio 2.2 and older
    private static final String BUILD_OUTPUT = "build/intermediates";

    // Build output location for Android Studio 2.3 and newer
    private static final String BUILD_OUTPUT_APP_LEVEL = "app/build/intermediates";

    public CustomRobolectricRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        FileFsFile res = FileFsFile.from(BUILD_OUTPUT, "/res/merged", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE);
        FileFsFile assets = FileFsFile.from(BUILD_OUTPUT, "assets", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE);
        FileFsFile manifest = FileFsFile.from(BUILD_OUTPUT, "manifests", "full", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE, "AndroidManifest.xml");

        // If above files does not exist in the specified paths, look them at the app folder level. This is needed
        // as Android studio 2.3 changed the working directory.
        if (!manifest.exists()) {
            manifest = FileFsFile.from(BUILD_OUTPUT_APP_LEVEL, "manifests", "full", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE, "AndroidManifest.xml");
        }

        if (!res.exists()) {
            res = FileFsFile.from(BUILD_OUTPUT_APP_LEVEL, "/res/merged", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE);
        }

        if (!assets.exists()) {
            assets = FileFsFile.from(BUILD_OUTPUT_APP_LEVEL, "assets", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE);
        }

        return new AndroidManifest(manifest, res, assets) {
            @Override
            public Class getRClass() {
                return R.class;
            }
            public int getTargetSdkVersion() {
                return Build.VERSION_CODES.LOLLIPOP;
            }
        };
    }
}