package com.example.heartinfei.testsample.base;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */

public abstract class WaitProgress<T extends Activity> implements IdlingResource {
    private String name;
    private T activity;
    private ResourceCallback callback;

    public WaitProgress(String name, T activity) {
        this.name = name;
        this.activity = activity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isIdleNow() {
        boolean result = check(activity);
        if (result) {
            callback.onTransitionToIdle();
        }
        return result;
    }

    /**
     * @return true 异步条件结束
     */
    protected abstract boolean check(T t);

    @Override
    public void registerIdleTransitionCallback(ResourceCallback c) {
        this.callback = c;
    }
}
