package br.com.gms.github.presentation.userList.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.com.gms.github.core.domain.model.GitUserDomain

internal class UserListDiffCallBack : DiffUtil.ItemCallback<GitUserDomain>() {
    override fun areItemsTheSame(
        oldItem: GitUserDomain,
        newItem: GitUserDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GitUserDomain,
        newItem: GitUserDomain
    ): Boolean {
        return oldItem == newItem
    }
}