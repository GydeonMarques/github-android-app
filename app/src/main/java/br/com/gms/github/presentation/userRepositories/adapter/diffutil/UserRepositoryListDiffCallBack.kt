package br.com.gms.github.presentation.userRepositories.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.com.gms.github.core.domain.model.GitRepositoryDomain

internal class UserRepositoryListDiffCallBack : DiffUtil.ItemCallback<GitRepositoryDomain>() {
    override fun areItemsTheSame(
        oldItem: GitRepositoryDomain,
        newItem: GitRepositoryDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GitRepositoryDomain,
        newItem: GitRepositoryDomain
    ): Boolean {
        return oldItem == newItem
    }
}