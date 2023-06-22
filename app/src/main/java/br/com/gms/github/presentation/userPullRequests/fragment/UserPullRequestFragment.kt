package br.com.gms.github.presentation.userPullRequests.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import br.com.gms.github.core.util.paging.PagingLoadStateAdapter
import br.com.gms.github.databinding.FragmentUserPullRequestBinding
import br.com.gms.github.presentation.userPullRequests.adapter.UserPullRequestAdapter
import br.com.gms.github.presentation.userPullRequests.viewmodel.UserPullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserPullRequestFragment : Fragment() {

    private val args by navArgs<UserPullRequestFragmentArgs>()
    private lateinit var binding: FragmentUserPullRequestBinding
    private val viewModel by viewModels<UserPullRequestViewModel>()
    private val gitRepositoryPullsAdapter: UserPullRequestAdapter by lazy { UserPullRequestAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserPullRequestBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setActions()
        setObservables()
        loadAllPullsOfRepository()
    }

    private fun setView() {
        with(binding.layoutUserPullRequest) {
            recyclerView.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                adapter = gitRepositoryPullsAdapter.run {
                    withLoadStateHeaderAndFooter(
                        header = PagingLoadStateAdapter(this),
                        footer = PagingLoadStateAdapter(this)
                    )
                }
            }
        }
    }

    private fun setActions() {
        gitRepositoryPullsAdapter.apply {
            addOnItemClickListener { model ->
                model.pullURL?.let { openPullRequestPage(it) }
            }
        }
    }

    private fun setObservables() {
        gitRepositoryPullsAdapter.apply {

            addLoadStateListener {
                it.refresh
                when (it.refresh) {
                    is LoadState.Loading -> setLoadingLayout()
                    is LoadState.NotLoading -> setSuccessLayout(itemCount)
                    is LoadState.Error -> setFailureLayout(it.refresh as LoadState.Error)
                }
            }

            lifecycleScope.launchWhenCreated {
                viewModel.state.collectLatest {
                    submitData(it)
                }
            }
        }
    }

    private fun loadAllPullsOfRepository() {
        viewModel.getUserRepositoryPullRequest(args.username, args.repositoryName)
    }

    private fun setLoadingLayout() {
        binding.apply {
            layoutError.root.isVisible = false
            layoutEmpty.root.isVisible = false
            layoutLoading.root.isVisible = true
            layoutUserPullRequest.root.isVisible = false
        }
    }

    private fun setSuccessLayout(itemCount: Int) {
        binding.apply {
            layoutError.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutEmpty.root.isVisible = itemCount <= 0
            layoutUserPullRequest.root.isVisible = itemCount > 0
        }
    }

    private fun setFailureLayout(state: LoadState.Error) {
        binding.apply {
            layoutError.root.isVisible = true
            layoutEmpty.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutUserPullRequest.root.isVisible = false
            layoutError.apply {
                root.isVisible = true
                state.error.message?.let { error ->
                    binding.layoutError.textViewDescription.text = error
                }
            }
        }
    }

    private fun openPullRequestPage(url: String) {
        ContextCompat.startActivity(requireContext(), Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.parse(url)
        }, null)
    }
}