package com.appstreet.githubrepotask

import android.app.Application
import com.appstreet.githubrepotask.di.component.ApplicationComponent
import com.appstreet.githubrepotask.di.component.DaggerApplicationComponent
import com.appstreet.githubrepotask.di.module.ApplicationModule

open class App : Application() {
    open val baseUrl: String
        get() = "https://github-trending-api.now.sh/"

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDi()
    }
    private fun initDi() {
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}