package com.blackcard.logan.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by Logan on 2019/3/27.
 */
public class MyApplication extends Application {
    private boolean isRunInBackground;//是否运行在后台
    private int appCount;//当前启动了多少个页面

    @Override
    public void onCreate() {
        super.onCreate();
        //切换前台后台监听
        rigistBackgoundCallBack();
        initTtf();
        initToast();
    }

    /**
     * 全局字体初始化
     */
    private void initTtf() {
        /*ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/seal.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());*/
    }

    private void initToast() {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
    }

    public boolean isRunInBackground() {
        return isRunInBackground;
    }

    /**
     * 从后台回到前台需要执行的逻辑
     *
     * @param activity 当前的activity
     */
    private void back2App(Activity activity) {
    }

    /**
     * 离开应用 压入后台或者退出应用
     *
     * @param activity
     */
    private void leaveApp(Activity activity) {
    }


    private void rigistBackgoundCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
                if (isRunInBackground) {
                    isRunInBackground = false;
                    //应用从后台回到前台 需要做的操作
                    back2App(activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
                if (appCount == 0) {
                    isRunInBackground = true;
                    //应用进入后台 需要做的操作
                    leaveApp(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }
}
