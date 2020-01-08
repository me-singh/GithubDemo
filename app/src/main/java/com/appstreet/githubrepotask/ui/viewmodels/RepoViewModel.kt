package com.appstreet.githubrepotask.ui.viewmodels
import androidx.databinding.BaseObservable
import com.appstreet.githubrepotask.models.TrendingRepos
import java.text.NumberFormat

class RepoViewModel : BaseObservable() {
    var repos: TrendingRepos? = null
        set(value) {
            field = value
            notifyChange()
        }

    val author: String
        get() = repos?.author.orEmpty()
    val repoName: String
        get() = repos?.name.orEmpty()
    val description: String
        get() = repos?.description.orEmpty()
    val language: String
        get() = repos?.language.orEmpty()
    val languageColor: String
        get() = repos?.languageColor.orEmpty()
    val stars: String
        get() = NumberFormat.getIntegerInstance().format(repos?.stars ?: 0)
    val forks: String
        get() = NumberFormat.getIntegerInstance().format(repos?.forks ?: 0)
    val imageUrl: String
        get() = repos?.avatar.orEmpty()

}