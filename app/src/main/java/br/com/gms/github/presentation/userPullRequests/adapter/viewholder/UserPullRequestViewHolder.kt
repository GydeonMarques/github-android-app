package br.com.gms.github.presentation.userPullRequests.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.gms.github.R
import br.com.gms.github.core.domain.model.GitPullRequestDomain
import br.com.gms.github.core.util.date.convertFromUSFormatToDateBR
import br.com.gms.github.core.util.image.loadImageByUrl
import br.com.gms.github.databinding.LayoutRepositoryCardPullItemBinding

typealias OnPullRequestItemClickListener = ((model: GitPullRequestDomain) -> Unit)?

internal class UserPullRequestViewHolder(
    private val context: Context,
    private val binding: LayoutRepositoryCardPullItemBinding,
    private val onItemClickListener: OnPullRequestItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
            onItemClickListener: OnPullRequestItemClickListener
        ): UserPullRequestViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutRepositoryCardPullItemBinding.inflate(inflater, parent, false)
            return UserPullRequestViewHolder(parent.context, binding, onItemClickListener)
        }
    }

    fun bindView(model: GitPullRequestDomain) {
        binding.apply {

            onItemClickListener?.let { cardGitRepositoryPull.setOnClickListener { it(model) } }

            layoutRepositoryItem.apply {
                textViewRepositoryTitle.text = model.title
                textViewRepositoryDescription.text = model.body
                textViewRepositoryDescription.isVisible = model.body.isNotEmpty()
            }

            layoutUserItem.apply {
                imageViewUser.loadImageByUrl(model.user.avatarUrl)
                textViewFullName.text = model.user.login
                textViewUsername.text = model.user.login
            }

            textViewPrCreateAt.text = context.getString(
                R.string.create_at, model.createdAt.convertFromUSFormatToDateBR()
            )
        }
    }
}
