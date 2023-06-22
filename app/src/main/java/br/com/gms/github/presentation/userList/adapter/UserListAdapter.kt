package br.com.gms.github.presentation.userList.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.presentation.userList.adapter.diffutil.UserListDiffCallBack
import br.com.gms.github.presentation.userList.adapter.viewholder.OnUserItemClickListener
import br.com.gms.github.presentation.userList.adapter.viewholder.UserListViewHolder

internal class UserListAdapter : PagingDataAdapter<GitUserDomain, UserListViewHolder>(
    UserListDiffCallBack()
) {

    private var onItemClickListener: OnUserItemClickListener = null

    fun addOnItemClickListener(onItemClickListener: OnUserItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder.getInstance(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}