package com.example.heartinfei.testsample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.codingmaster.slib.S;
import com.example.heartinfei.testsample.base.BaseActivityTest;
import com.example.heartinfei.testsample.base.WaitProgress;
import com.example.heartinfei.testsample.target.NetActivity;
import com.example.heartinfei.testsample.widget.ProgressLayout;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestNetActivity extends BaseActivityTest<MainActivity> {
    private Pattern pattern = Pattern.compile("<title>.*?</title>");

    @Rule
    public ActivityTestRule<MainActivity> actRule = new ActivityTestRule<>(MainActivity.class);

    @Override
    protected ActivityTestRule<MainActivity> getActivityRule() {
        return actRule;
    }
    @Before
    public void tearUp(){
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);
    }
    @Test
    public void testNetAct(){
        Espresso.onView(ViewMatchers.withId(R.id.rlv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        //验证启动的Activity正确
        Espresso.onView(ViewMatchers.isRoot())
                .check(new ViewAssertion() {
                    @Override
                    public void check(View view, NoMatchingViewException noViewFoundException) {
                        View v = view;
                        NoMatchingViewException n = noViewFoundException;
                    }
                });
        Assert.assertTrue(getCurrentActivity() instanceof NetActivity);

        Espresso.onView(ViewMatchers.withId(R.id.btn))
                .perform(ViewActions.click());

        NetActivity act = (NetActivity) getCurrentActivity();
        final WaitProgress<NetActivity> waitForResponse = new WaitProgress<NetActivity>("WaitForLoadingData", act) {
            @Override
            protected boolean check(NetActivity act) {
                return !((ProgressLayout) act.findViewById(R.id.progressLayout)).isProgressing();
            }
        };
        S.i("");
        Assert.assertTrue(IdlingRegistry.getInstance().register(waitForResponse));
        S.i("");
        String data = getText(Espresso.onView(ViewMatchers.withId(R.id.tv)));
        //验证数据数据请求
        Assert.assertTrue(pattern.matcher(data).find());
        Assert.assertTrue(IdlingRegistry.getInstance().unregister(waitForResponse));
        S.i("");
        Espresso.onView(ViewMatchers.withId(R.id.btn_clean))
                .perform(ViewActions.click());
        S.i("");
        IdlingRegistry.getInstance().register(waitForResponse);
        //验证数据清空
        Espresso.onView(ViewMatchers.withId(R.id.tv))
                .check(ViewAssertions.matches(ViewMatchers.withText("123")));
        S.i("");
        IdlingRegistry.getInstance().unregister(waitForResponse);
        S.i("");
    }
}
