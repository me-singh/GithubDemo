package com.appstreet.githubrepotask.ui.repolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appstreet.githubrepotask.App
import com.appstreet.githubrepotask.R
import com.appstreet.githubrepotask.data.RepoService
import com.appstreet.githubrepotask.databinding.ActivityMainBinding
import com.appstreet.githubrepotask.ui.GithubRepoAdapter
import com.appstreet.githubrepotask.ui.viewmodels.GitHubRepoViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private val repoAdapter = GithubRepoAdapter()
    private lateinit var viewModel: GitHubRepoViewModel
    private lateinit var binding: ActivityMainBinding


    @Inject lateinit var repoService : RepoService

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return GitHubRepoViewModel(repoService) as T
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GitHubRepoViewModel::class.java)
        binding.viewModel = viewModel
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.repoList.observe(
            this,
            Observer {
                it?.let(repoAdapter::items::set)
            }
        )
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.repoList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter
    }
}
