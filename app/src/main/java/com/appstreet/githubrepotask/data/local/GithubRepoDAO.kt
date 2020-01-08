package com.appstreet.githubrepotask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appstreet.githubrepotask.models.TrendingRepos

@Dao
interface GithubRepoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(trendingRepos: List<TrendingRepos>)

    @Query("DELETE FROM TrendingRepos")
    suspend fun deleteAllRepos(): Int

    @Query("SELECT * FROM TrendingRepos")
    suspend fun getAllCurrentRepos(): List<TrendingRepos>

    @Query("SELECT * FROM TrendingRepos WHERE name = :name")
    suspend fun getRepoByName(name: String): TrendingRepos
}