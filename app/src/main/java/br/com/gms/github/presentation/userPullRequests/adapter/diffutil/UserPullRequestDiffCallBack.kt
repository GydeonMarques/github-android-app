package br.com.gms.github.presentation.userPullRequests.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.com.gms.github.core.domain.model.GitPullRequestDomain

internal class UserPullRequestDiffCallBack : DiffUtil.ItemCallback<GitPullRequestDomain>() {
    override fun areItemsTheSame(
        oldItem: GitPullRequestDomain,
        newItem: GitPullRequestDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GitPullRequestDomain,
        newItem: GitPullRequestDomain
    ): Boolean {
        return oldItem == newItem
    }
}