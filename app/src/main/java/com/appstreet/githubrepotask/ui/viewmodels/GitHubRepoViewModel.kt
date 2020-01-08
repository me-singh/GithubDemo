package com.appstreet.githubrepotask.ui.viewmodels

import androidx.lifecycle.*
import com.appstreet.githubrepotask.DispatcherProvider
import com.appstreet.githubrepotask.data.RepoService
import com.appstreet.githubrepotask.models.TrendingRepos
import com.appstreet.githubrepotask.ui.GithubRepoListState
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GitHubRepoViewModel (
    private val githubRepository: RepoService,
    private val dispatch: DispatcherProvider = DispatcherProvider()
) : BaseObservableViewModel() {
    private val state = MutableLiveData<GithubRepoListState>().apply {
        value = GithubRepoListState.Loading
    }

    val repoList: LiveData<ArrayList<TrendingRepos>?> = Transformations.map(state) {
        state -> (state as? GithubRepoListState.Result)?.data
    }

    val showLoading: LiveData<Boolean> = Transformations.map(state) {
        state -> state is GithubRepoListState.Loading
    }

    val showError: LiveData<Boolean> = Transformations.map(state) {
        state -> state is GithubRepoListState.Error
    }

    val showData: LiveData<Boolean> = Transformations.map(state) { state ->
        state is GithubRepoListState.Result
    }

    init {
        getTrendingRepos()
    }

    private fun getTrendingRepos() {
        viewModelScope.launch {
            startLoading()
            val newState = withContext(dispatch.IO) {
                try {
                    val response = githubRepository.getTrendingRepos()

                    return@withContext GithubRepoListState.Result(
                        response
                    )
                } catch (error : Throwable) {
                    return@withContext GithubRepoListState.Error(error)
                }
            }

            setState(newState)
        }
    }

    private fun startLoading() {
        setState(GithubRepoListState.Loading)
    }

    private fun setState(newState: GithubRepoListState) {
        this.state.value = newState
        notifyChange()
    }

    fun retry(){
        getTrendingRepos()
    }

}