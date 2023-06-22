package br.com.gms.github.presentation.userRepositories.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.presentation.userRepositories.adapter.diffutil.UserRepositoryListDiffCallBack
import br.com.gms.github.presentation.userRepositories.adapter.viewholder.OnRepositoryItemClickListener
import br.com.gms.github.presentation.userRepositories.adapter.viewholder.UserRepositoryListViewHolder

internal class UserRepositoryListAdapter :
    PagingDataAdapter<GitRepositoryDomain, UserRepositoryListViewHolder>(UserRepositoryListDiffCallBack()) {

    private var onItemClickListener: OnRepositoryItemClickListener = null

    fun addOnItemClickListener(onItemClickListener: OnRepositoryItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoryListViewHolder {
        return UserRepositoryListViewHolder.getInstance(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: UserRepositoryListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}