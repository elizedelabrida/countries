package com.elize.countries

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher

abstract class BaseRobot {
    fun checkViewIsDisplayed(resId: Int): ViewInteraction =
        onView(withId(resId))
            .check(matches(isDisplayed()))

    fun scrollToPosition(resId: Int, position: Int): ViewInteraction =
        onView(withId(resId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
            .check(matches(isDisplayed()))

    fun findItemInRecyclerView(recyclerId: Int, withText: String) {
        findItemInRecyclerView(recyclerId, withText(withText))
    }

    private fun findItemInRecyclerView(recyclerId: Int, matcher: Matcher<View>?) =
        onView(withId(recyclerId))
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(matcher),
                    scrollTo()
                )
            )


    fun sleep(times: Long) = apply {
        Thread.sleep(times)
    }

}