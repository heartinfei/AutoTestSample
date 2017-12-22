package com.example.heartinfei.testsample;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.manager.TargetTracker;
import com.codingmaster.slib.S;
import com.example.heartinfei.testsample.target.HelloWorldActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author 王强 on 2017/12/21 249346528@qq.com
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MyTest {
    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);

    int position = 0;
    @Test
    public void testRecy() {
        Espresso.onView(ViewMatchers.withId(R.id.rlv))
//                .perform(RecyclerViewActions.scrollToPosition(19));
                .check(ViewAssertions.matches(new TypeSafeMatcher<View>() {
                    @Override
                    protected boolean matchesSafely(View item) {
                        RecyclerView rlv = ((RecyclerView) item);
                        MainAdapter adapter = (MainAdapter) rlv.getAdapter();
                        for (int i = 0; i < adapter.getItemCount(); i++) {
                            ActivityInfo info = adapter.getItem(i);
                            String act  =info.name;
                            String tarName = HelloWorldActivity.class.getName();
                            boolean result = TextUtils.equals(act, tarName);
                            position = i;
                            if (result){
                                return result;
                            }
                        }
                        return false;
                    }

                    @Override
                    public void describeTo(Description description) {

                    }
                }));


        Espresso.onView(ViewMatchers.isRoot())
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return ViewMatchers.isRoot();
                    }

                    @Override
                    public String getDescription() {
                        return null;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        uiController.loopMainThreadForAtLeast(5000);
                    }
                });

        Espresso.onView(ViewMatchers.withId(R.id.rlv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position,ViewActions.click()));
//                .perform(RecyclerViewActions.scrollTo(new Matcher<View>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        S.i("");
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }))
//                .perform(RecyclerViewActions.scrollToHolder(new TypeSafeMatcher<MainAdapter.VH>() {
//                    @Override
//                    protected boolean matchesSafely(MainAdapter.VH item) {
//                        String s = HelloWorldActivity.class.getName();
//                        String s1 = item.gettext();
//                        boolean b = TextUtils.equals(s, s1);
//                        return b;
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }));
//                .perform(RecyclerViewActions.actionOnHolderItem(new TypeSafeMatcher<MainAdapter.VH>() {
//                    @Override
//                    protected boolean matchesSafely(MainAdapter.VH item) {
//                        return TextUtils.equals(HelloWorldActivity.class.getName(), item.getString());
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                },ViewActions.click()));
    }
}
