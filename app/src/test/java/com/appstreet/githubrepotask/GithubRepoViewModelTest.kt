package com.appstreet.githubrepotask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appstreet.githubrepotask.models.TrendingRepos
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SuppressWarnings("UNCHECKED_CAST")
class GithubRepoViewModelTest {
    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val repoListTestRobot = GithubRepoViewModelTestRobot()

    @Test
    fun loadData() {
        val testUrl1 = "https://github.com/nikhilmaurya10/LineMarkerView"
        val testRepo1 = TrendingRepos(author = "nikhilmaurya10", avatar = "sadsa", currentPeriodStars = 0, description = "sadsa", forks = 0, language = "Java",languageColor = "red", name = "LineMarkerView", stars = 1, url = testUrl1)

        val testUrl2 = "https://github.com/nikhilmaurya10/LineMarkerView2"
        val testRepo2 = TrendingRepos(author = "nikhilmaurya10", avatar = "sadsa", currentPeriodStars = 0, description = "sadsa", forks = 0, language = "Java",languageColor = "red", name = "LineMarkerView", stars = 1, url = testUrl2)
        val list = ArrayList<TrendingRepos>()
        list.add(testRepo1)
        list.add(testRepo2)

        repoListTestRobot.mockRepoListResponse(list)
            .buildViewModel()
            .assertShowLoading(false)
            .assertShowError(false)
            .assertShowData(true)


    }

    @Test
    fun loadError() {
        repoListTestRobot.mockRepoListError()
            .buildViewModel()
            .assertShowData(false)
            .assertShowLoading(false)
            .assertShowError(true)
    }

    @Test
    fun getData() {
        val testRepoList = arrayListOf(TrendingRepos(url = "asdsadsad"))

        repoListTestRobot.mockRepoListResponse(testRepoList)
            .buildViewModel()
            .assertShowLoading(false)
            .assertShowError(false)
            .assertShowData(true)
            .assertGithubRepoList(testRepoList)
    }
}