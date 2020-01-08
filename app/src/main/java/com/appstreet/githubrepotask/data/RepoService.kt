package com.appstreet.githubrepotask.data

import com.appstreet.githubrepotask.data.local.GithubReposDb
import com.appstreet.githubrepotask.data.remote.GithubAPI
import com.appstreet.githubrepotask.models.TrendingRepos
import kotlin.collections.ArrayList

open class RepoService(
    private val database: GithubReposDb,
    private val api: GithubAPI
) : GithubRepository {

    override suspend fun getTrendingRepos(): ArrayList<TrendingRepos> {
        return api.getTrendingReposAsync().await().also {
            database.trendingReposDAO().deleteAllRepos()
            database.trendingReposDAO().insertRepos(it)
        }

    }
}
