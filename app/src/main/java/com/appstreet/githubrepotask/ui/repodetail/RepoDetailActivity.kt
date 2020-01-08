package com.appstreet.githubrepotask.ui.repodetail

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.appstreet.githubrepotask.R
import com.appstreet.githubrepotask.models.TrendingRepos
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_repo_detail.*

class RepoDetailActivity : AppCompatActivity() {
    private var repoDetail : TrendingRepos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        repoDetail = intent.getParcelableExtra("data")
        repoDetail?.apply {
            Glide.with(this@RepoDetailActivity).load(avatar).apply(RequestOptions().circleCrop()).into(avatarImg)
            userName.text = author
            repoName.text = Html.fromHtml("<a href=$url>$name</a> ")
            repoName.movementMethod = LinkMovementMethod.getInstance();
            repoDesc.text = description
            languageTV.text = language
            starsTV.text = stars.toString()
            forksTV.text = forks.toString()
        }
    }
}