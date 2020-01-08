package com.appstreet.githubrepotask.di.component

import com.appstreet.githubrepotask.App
import com.appstreet.githubrepotask.ui.repolist.MainActivity
import com.appstreet.githubrepotask.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(myApplication: App)

    fun inject(myActivity: MainActivity)

}