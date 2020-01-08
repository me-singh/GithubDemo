package com.appstreet.githubrepotask.tests


import androidx.room.Room
import androidx.test.rule.ActivityTestRule
import com.appstreet.githubrepotask.data.local.GithubRepoDAO
import com.appstreet.githubrepotask.data.local.GithubReposDb
import com.appstreet.githubrepotask.models.TrendingRepos
import com.appstreet.githubrepotask.ui.repolist.MainActivity
import kotlinx.coroutines.runBlocking
import org.junit.*

class RepoDBTest {
    private lateinit var database: GithubReposDb
    private lateinit var dao: GithubRepoDAO

    @JvmField
    @Rule
    val mainActivity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        val context = mainActivity.activity
        database = Room.inMemoryDatabaseBuilder(context, GithubReposDb::class.java)
            .allowMainThreadQueries().build()
        dao = database.trendingReposDAO()
    }

    @After
    fun tearDown() {
        runBlocking {
            dao.deleteAllRepos()
            database.close()
        }
    }

    @Test
    fun insertReadRepo() {
        runBlocking {
            val testName = "AwesomeRepo"
            val testRespos = listOf(TrendingRepos(name = testName))
            dao.insertRepos(testRespos)

            val result = dao.getRepoByName(testName)

            Assert.assertEquals(testRespos[0].name, result.name)
        }
    }
}