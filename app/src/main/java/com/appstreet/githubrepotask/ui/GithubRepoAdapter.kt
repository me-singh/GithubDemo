package com.appstreet.githubrepotask.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appstreet.githubrepotask.databinding.LayoutRepoItemBinding
import com.appstreet.githubrepotask.models.TrendingRepos
import com.appstreet.githubrepotask.ui.viewmodels.RepoViewModel

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.ViewHolder>() {

    var items: ArrayList<TrendingRepos> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = LayoutRepoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, this::onRepoClicked)
    }

    private fun onRepoClicked(index: Int) {
        Log.d("sadasa","data $index")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = items[position]
        holder.bindRepo(repo)
    }


    class ViewHolder(
        private val binding: LayoutRepoItemBinding,
        private val repoClickListener: ((Int) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RepoViewModel()

        init {
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
                repoClickListener(adapterPosition)
            }
        }

        fun bindRepo(repo: TrendingRepos) {
            viewModel.repos = repo
            binding.executePendingBindings()
        }
    }
}