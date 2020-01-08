package com.appstreet.githubrepotask.data

import com.appstreet.githubrepotask.models.TrendingRepos


interface GithubRepository {
    suspend fun getTrendingRepos(): ArrayList<TrendingRepos>
}