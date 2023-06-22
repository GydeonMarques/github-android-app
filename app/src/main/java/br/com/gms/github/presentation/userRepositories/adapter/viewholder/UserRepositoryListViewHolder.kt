package br.com.gms.github.presentation.userRepositories.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gms.github.R
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.databinding.LayoutRepositoryCardItemBinding

typealias OnRepositoryItemClickListener = ((model: GitRepositoryDomain) -> Unit)?

internal class UserRepositoryListViewHolder(
    private val binding: LayoutRepositoryCardItemBinding,
    private val onItemClickListener: OnRepositoryItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
            onItemClickListener: OnRepositoryItemClickListener
        ): UserRepositoryListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutRepositoryCardItemBinding.inflate(inflater, parent, false)
            return UserRepositoryListViewHolder(binding, onItemClickListener)
        }
    }

    fun bindView(model: GitRepositoryDomain) {
        binding.apply {

            onItemClickListener?.let { cardGitRepository.setOnClickListener { it(model) } }

            layoutRepositoryItem.apply {
                textViewRepositoryTitle.text = model.name
                textViewRepositoryDescription.text = model.description.ifEmpty {
                    binding.root.context.getString(R.string.undefined)
                }
            }

            layoutRepositoryForksItem.apply {
                textViewRepositoryForks.text = model.forksCount.toString()
                textViewRepositoryRates.text = model.watchersCount.toString()
            }
        }
    }
}
