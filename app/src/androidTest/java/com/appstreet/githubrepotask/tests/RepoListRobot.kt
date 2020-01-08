package com.appstreet.githubrepotask.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.appstreet.githubrepotask.R
import com.appstreet.githubrepotask.RecyclerViewMatcher
import com.appstreet.githubrepotask.Util
import com.appstreet.githubrepotask.ui.repodetail.RepoDetailActivity

class RepoListRobot {

    fun assertDataDisplayed() = apply {
        onView(recyclerViewMatcher)
            .check(matches(isDisplayed()))
    }

    fun waitForCondition(idlingResource: IdlingResource?) = apply {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    fun assertRepoAtPosition(position: Int, repoName: String) = apply {
        val itemMatcher = RecyclerViewMatcher(recyclerViewId)
            .atPositionOnView(
                position,
                name
            )
        onView(itemMatcher)
            .check(matches(withText(repoName)))
    }

    fun clickItem(position: Int) = apply {
        val itemMatcher = RecyclerViewMatcher(recyclerViewId).atPosition(position)
        onView(itemMatcher).perform(click())
    }


    fun assertErrorDisplayed() {
        onView(errorViewMatcher).check(matches(isDisplayed()))
    }

    companion object {
        private const val recyclerViewId = R.id.repo_list
        private const val name = R.id.repoName
        private const val errorView = R.id.errorView

        private val recyclerViewMatcher = withId(R.id.repo_list)
        private val errorViewMatcher = withId(R.id.errorView)
    }
}