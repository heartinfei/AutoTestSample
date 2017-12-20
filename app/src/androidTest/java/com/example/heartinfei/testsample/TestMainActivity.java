package com.example.heartinfei.testsample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.codingmaster.slib.S;
import com.example.heartinfei.testsample.base.BaseActivityTest;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestMainActivity extends BaseActivityTest<MainActivity> {
    private int itemCount = 0;
    @Rule
    public ActivityTestRule<MainActivity> actRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void testAllItem() {
        ViewInteraction rlv = Espresso.onView(ViewMatchers.withId(R.id.rlv));
        updateItemCount(rlv);
        Assert.assertTrue(itemCount > 0);
        for (int i = 0; i < itemCount; i++) {
            rlv.perform(RecyclerViewActions.actionOnItemAtPosition(i, ViewActions.click()));
            //验证启动的Activity正确
            Assert.assertTrue(!(getCurrentActivity() instanceof MainActivity));
            Espresso.pressBack();
        }
    }

    private void updateItemCount(ViewInteraction interaction) {
        interaction.check((v, e) ->
                itemCount = ((RecyclerView) v).getAdapter().getItemCount());
    }

    @Override
    protected ActivityTestRule<MainActivity> getActivityRule() {
        return actRule;
    }
}
