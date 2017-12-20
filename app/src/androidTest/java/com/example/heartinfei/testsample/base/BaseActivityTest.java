package com.example.heartinfei.testsample.base;

import android.app.Activity;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

import java.util.Collection;

import static android.support.test.runner.lifecycle.Stage.RESUMED;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */
public abstract class BaseActivityTest<T extends Activity> {
    protected abstract ActivityTestRule<T> getActivityRule();
    /**
     * 主线程等待指定的时间
     *
     * @param millis
     * @return
     */
    protected ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for millis.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
    Activity mActivity = null;
    protected Activity getCurrentActivity() {

        try {
            getActivityRule().runOnUiThread(()->{
//                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(RESUMED);

                if (resumedActivities.iterator().hasNext()) {
                    mActivity =resumedActivities.iterator().next();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return mActivity;
    }

    protected String getText(ViewInteraction viewInteraction) {
        final String[] result = {null};
        viewInteraction.perform(new ViewAction() {
            @Override
            public org.hamcrest.Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Get text from TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                result[0] = ((TextView) view).getText().toString();
            }
        });
        return result[0];
    }
}
