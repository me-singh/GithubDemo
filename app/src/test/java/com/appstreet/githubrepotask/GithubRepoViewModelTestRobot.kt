package com.appstreet.githubrepotask

import com.appstreet.githubrepotask.data.RepoService
import com.appstreet.githubrepotask.models.TrendingRepos
import com.appstreet.githubrepotask.ui.viewmodels.GitHubRepoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock

class GithubRepoViewModelTestRobot(
    private val mockRepository: RepoService = mock(RepoService::class.java),
    private val testDispatcherProvider: DispatcherProvider = DispatcherProvider(
        IO = Dispatchers.Unconfined,
        Main = Dispatchers.Unconfined
    )
) {
    private lateinit var viewModel: GitHubRepoViewModel

    fun mockRepoListResponse(response: ArrayList<TrendingRepos>) = apply {
        runBlocking {
            whenever(mockRepository.getTrendingRepos()).thenReturn(response)
        }
    }

    fun mockRepoListError(error: Throwable = IllegalArgumentException()) = apply {
        runBlocking {
            whenever(mockRepository.getTrendingRepos()).thenThrow(error)
        }
    }

    fun buildViewModel() = apply {
        viewModel = GitHubRepoViewModel(
            mockRepository,
            testDispatcherProvider
        )
    }

    fun assertShowLoading(showLoading: Boolean) = apply{
        assertEquals(showLoading, viewModel.showLoading.testObserver().observedValue)
    }

    fun assertShowData(showData: Boolean) = apply {
        assertEquals(showData, viewModel.showData.testObserver().observedValue)
    }

    fun assertShowError(showError: Boolean) = apply {
        assertEquals(showError, viewModel.showError.testObserver().observedValue)
    }

    fun assertGithubRepoList(githubRepoList: ArrayList<TrendingRepos>) = apply {
        assertEquals(githubRepoList, viewModel.repoList.testObserver().observedValue)
    }
}