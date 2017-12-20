package com.example.heartinfei.testsample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.heartinfei.testsample.target.HelloWorldActivity;
import com.example.heartinfei.testsample.target.IntentTestActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * @author 王强 on 2017/12/18 249346528@qq.com
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntentTestActivityTest {
    @Rule
    public ActivityTestRule<IntentTestActivity> mRule = new ActivityTestRule<>(IntentTestActivity.class);

    @Before
    public void tearUp(){
        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }

    @Test
    public void testIntent() {
        Espresso.onView(ViewMatchers.withId(R.id.button))
                .perform(ViewActions.click());
        String name = HelloWorldActivity.class.getName();
        intended(hasComponent(name));
    }

}
