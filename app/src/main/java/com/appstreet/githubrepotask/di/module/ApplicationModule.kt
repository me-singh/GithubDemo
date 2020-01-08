package com.appstreet.githubrepotask.di.module

import android.app.Application
import androidx.room.Room
import com.appstreet.githubrepotask.App
import com.appstreet.githubrepotask.data.RepoService
import com.appstreet.githubrepotask.data.local.GithubReposDb
import com.appstreet.githubrepotask.data.remote.GithubAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {


    @Singleton
    @Provides
    fun getRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl((mApplication as App).baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    fun getGithubService(database: GithubReposDb, api: GithubAPI) : RepoService {
        return RepoService(database, api)
    }

    @Provides
    fun getGithubApi(retrofit : Retrofit): GithubAPI {
        return retrofit.create(GithubAPI::class.java)
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun getReposDb() : GithubReposDb{
        return Room.databaseBuilder(mApplication, GithubReposDb::class.java,"repo.db")
            .allowMainThreadQueries().build()
    }

}