package com.tencent.shadow.demo.host;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.tencent.shadow.core.common.LoggerFactory;

public class HostApplication extends Application {
    private static Application sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        detectNonSdkApiUsageOnAndroidP();

        LoggerFactory.setILoggerFactory(new DemoLoggerFactory());

        PluginHelper.getInstance().init(this);

    }

    private static void detectNonSdkApiUsageOnAndroidP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            builder.penaltyDeath();
            builder.detectNonSdkApiUsage();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    public static Application getApp() {
        return sApp;
    }
}