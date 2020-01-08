package com.appstreet.githubrepotask.tests

import android.view.View
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.appstreet.githubrepotask.BuildConfig
import com.appstreet.githubrepotask.ErrorDispatcher
import com.appstreet.githubrepotask.R
import com.appstreet.githubrepotask.ViewVisibilityIdlingResource
import com.appstreet.githubrepotask.ui.repolist.MainActivity
import com.gojektask.githubrepo.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val mockWebServer = MockWebServer()

    private var progressBarGoneIdlingResource: ViewVisibilityIdlingResource? = null

    @Before
    fun setup() {
        mockWebServer.start(BuildConfig.PORT)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()

        IdlingRegistry.getInstance().unregister(progressBarGoneIdlingResource)
    }


    @Test
    fun displayError_whenErrorDispatchedAndNoCachedResult() {
        mockWebServer.dispatcher = ErrorDispatcher()
        activityTestRule.launchActivity(null)
        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progress_bar),
                View.GONE
            )

        RepoListRobot()
            .waitForCondition(progressBarGoneIdlingResource)
            .assertErrorDisplayed()
    }

    @Test
    fun displayRepoList_whenResultDispatched() {
        mockWebServer.dispatcher = ResultDispatcher()
        activityTestRule.launchActivity(null)
        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progress_bar),
                View.GONE
            )

        RepoListRobot()
            .waitForCondition(progressBarGoneIdlingResource)
            .assertDataDisplayed()
            .assertRepoAtPosition(0, "USA-Constitution")
            .assertRepoAtPosition(1, "polynote")
            .assertRepoAtPosition(2, "Blawn")
    }

    @Test
    fun clickItem() {
        mockWebServer.dispatcher = ResultDispatcher()
        activityTestRule.launchActivity(null)
        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progress_bar),
                View.GONE
            )

        RepoListRobot()
            .waitForCondition(progressBarGoneIdlingResource)
            .assertDataDisplayed()
            .clickItem(0)


    }

}