/*package com.example.sep4_android;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeFragmentCo2DisplayTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void homeFragmentCo2DisplayTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                5)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_startDate), withText("Button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment_content_main),
                                        0),
                                16),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction checkableImageButton = onView(
                allOf(withId(androidx.appcompat.R.id.mtrl_picker_header_toggle), withContentDescription("Switch to calendar input mode"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.mtrl_picker_header),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        checkableImageButton.perform(click());

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(allOf(withId(androidx.appcompat.R.id.month_grid),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(9);
        materialTextView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(androidx.appcompat.R.id.confirm_button), withText("OK"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.date_picker_actions),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.mtrl_calendar_main_pane),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_endDate), withText("Button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment_content_main),
                                        0),
                                15),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction checkableImageButton2 = onView(
                allOf(withId(androidx.appcompat.R.id.mtrl_picker_header_toggle), withContentDescription("Switch to calendar input mode"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.mtrl_picker_header),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        checkableImageButton2.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withId(androidx.appcompat.R.id.month_grid),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(9);
        materialTextView2.perform(click());

        DataInteraction materialTextView3 = onData(anything())
                .inAdapterView(allOf(withId(androidx.appcompat.R.id.month_grid),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(23);
        materialTextView3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(androidx.appcompat.R.id.confirm_button), withText("OK"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.date_picker_actions),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.mtrl_calendar_main_pane),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_submit), withText("submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment_content_main),
                                        0),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_co2), withText("1.0, 2.0, 3.0, 4.0, 5.0, "),
                        withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                        isDisplayed()));
        textView.check(matches(withText("1.0, 2.0, 3.0, 4.0, 5.0, ")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
*/