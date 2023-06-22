package br.com.gms.github.core.util.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import br.com.gms.github.R
import br.com.gms.github.databinding.PagingLoadStateItemBinding

class PagingLoadStateViewHolder(
    private val context: Context,
    private val binding: PagingLoadStateItemBinding,
    private val onClickListenerTryAgain: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
            onClickListenerTryAgain: () -> Unit
        ): PagingLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PagingLoadStateItemBinding.inflate(inflater, parent, false)
            return PagingLoadStateViewHolder(parent.context, binding, onClickListenerTryAgain)
        }
    }

    fun bindView(state: LoadState) {
        binding.apply {
            retryButton.isVisible = state is LoadState.Error
            viewAnimation.isVisible = state is LoadState.Loading
            retryButton.setOnClickListener { onClickListenerTryAgain() }

            textViewMessage.text = when (state) {
                is LoadState.Error -> state.error.message ?: getString(R.string.there_was_an_error_loading_the_data)
                is LoadState.Loading -> getString(R.string.loading_data__please_wait)
                else -> getString(R.string.there_was_an_error_loading_the_data)
            }
        }
    }

    private fun getString(@StringRes res: Int): String {
        return context.getString(res)
    }
}