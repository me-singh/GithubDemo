package com.appstreet.githubrepotask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appstreet.githubrepotask.data.Converters
import com.appstreet.githubrepotask.models.TrendingRepos


@Database(entities = [TrendingRepos::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GithubReposDb : RoomDatabase() {
    abstract fun trendingReposDAO() : GithubRepoDAO
}