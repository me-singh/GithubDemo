package com.appstreet.githubrepotask.tests

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.appstreet.githubrepotask.R
import com.appstreet.githubrepotask.models.TrendingRepos
import com.appstreet.githubrepotask.ui.repodetail.RepoDetailActivity
import com.appstreet.githubrepotask.ui.repolist.MainActivity
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class RepoDetailActivityTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule(RepoDetailActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun ensureIntentDataIsDisplayed() {
        val repo = TrendingRepos("nikhil","newRepo")
        val intent = Intent(ApplicationProvider.getApplicationContext(), RepoDetailActivity::class.java)
        intent.putExtra("data",repo)
        val activity = activityRule.launchActivity(intent)
        val viewById: View = activity.findViewById(R.id.userName)
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(TextView::class.java))
        val textView = viewById as TextView
        assertThat(textView.text.toString(), `is`("nikhil"))
    }

}