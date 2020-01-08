package com.appstreet.githubrepotask.ui

import com.appstreet.githubrepotask.models.TrendingRepos


sealed class GithubRepoListState {
    object Loading : GithubRepoListState()
    class Result(val data : ArrayList<TrendingRepos>) : GithubRepoListState()
    class Error(val error: Throwable?) : GithubRepoListState()
}
