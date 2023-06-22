package br.com.gms.github.presentation.userList.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gms.github.core.util.image.loadImageByUrl
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.databinding.LayoutCardUserItemBinding

typealias OnUserItemClickListener = ((model: GitUserDomain) -> Unit)?

internal class UserListViewHolder(
    private val binding: LayoutCardUserItemBinding,
    private val onItemClickListener: OnUserItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
            onItemClickListener: OnUserItemClickListener
        ): UserListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutCardUserItemBinding.inflate(inflater, parent, false)
            return UserListViewHolder(binding, onItemClickListener)
        }
    }

    fun bindView(model: GitUserDomain) {
        binding.apply {

            layoutUserItem.apply {
                imageViewUser.loadImageByUrl(model.avatarUrl)
                textViewUsername.text = model.login
                textViewFullName.text = model.login
            }

            onItemClickListener?.let {
                cardGitUser.setOnClickListener {
                    it(model)
                }
            }
        }
    }
}