package com.appstreet.githubrepotask.data.remote

import com.appstreet.githubrepotask.models.TrendingRepos
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface GithubAPI {
    @GET("repositories")
    fun getTrendingReposAsync() : Deferred<ArrayList<TrendingRepos>>

    companion object {
        fun getInstance(baseUrl: String): GithubAPI {
            val interceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
                .create(GithubAPI::class.java)
        }
    }
}

