package br.com.gms.github.presentation.userPullRequests.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.gms.github.core.domain.model.GitPullRequestDomain
import br.com.gms.github.presentation.userPullRequests.adapter.diffutil.UserPullRequestDiffCallBack
import br.com.gms.github.presentation.userPullRequests.adapter.viewholder.UserPullRequestViewHolder
import br.com.gms.github.presentation.userPullRequests.adapter.viewholder.OnPullRequestItemClickListener

internal class UserPullRequestAdapter : PagingDataAdapter<GitPullRequestDomain, UserPullRequestViewHolder>(
    UserPullRequestDiffCallBack()
) {

    private var onItemClickListener: OnPullRequestItemClickListener = null

    fun addOnItemClickListener(onItemClickListener: OnPullRequestItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPullRequestViewHolder {
        return UserPullRequestViewHolder.getInstance(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: UserPullRequestViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}