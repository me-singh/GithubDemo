package com.appstreet.githubrepotask

import android.content.Context
import android.view.View
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import androidx.recyclerview.widget.RecyclerView
import com.appstreet.githubrepotask.models.TrendingRepos
import com.appstreet.githubrepotask.ui.GithubRepoAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


object Util {
    fun asset(context: Context, path: String): String {
        try {
            val inputStream = context.assets.open(path)
            return inputStreamToString(inputStream, "UTF-8")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, charsetName)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }


    fun isSortedAlphabetically(recyclerViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            private val repoNames = ArrayList<String>()

            override fun matchesSafely(view: View): Boolean {
                val recyclerView = view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                val adapter = recyclerView.adapter as GithubRepoAdapter?

                repoNames.addAll(extractNames(adapter!!.items))
                val value = repoNames.map { it.toLowerCase() }.zipWithNext { a, b -> a <= b }.all { it }
                return value
            }

            private fun extractNames(repos: List<TrendingRepos>): Collection<String> {
                val repoNames = ArrayList<String>()
                for (repo in repos) {
                    repoNames.add(repo.name.orEmpty())
                }
                return repoNames
            }

            override fun describeTo(description: Description) {
                description.appendText("has items sorted alphabetically: $repoNames")
            }
        }
    }
}