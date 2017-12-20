package com.example.heartinfei.testsample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.heartinfei.testsample.target.HelloWorldActivity;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author 王强 on 2017/12/18 249346528@qq.com
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class HelloWorldTest {
    @Rule
    public ActivityTestRule<HelloWorldActivity> mRule = new ActivityTestRule<>(HelloWorldActivity.class);

    @Test
    public void testUI() {
        Espresso.onView(ViewMatchers.withId(R.id.tv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(new TypeSafeMatcher<View>() {
                    @Override
                    protected boolean matchesSafely(View item) {
                        return TextUtils.equals(((TextView) item).getText(),"Hello World");
                    }

                    @Override
                    public void describeTo(Description description) {
                        description.appendText("hhhhhh");
                    }
                }));
    }
}
