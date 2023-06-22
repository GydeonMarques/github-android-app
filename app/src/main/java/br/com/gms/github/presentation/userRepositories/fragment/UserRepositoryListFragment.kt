package br.com.gms.github.presentation.userRepositories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import br.com.gms.github.core.util.image.loadImageByUrl
import br.com.gms.github.core.util.paging.PagingLoadStateAdapter
import br.com.gms.github.databinding.FragmentUserRepositoryListBinding
import br.com.gms.github.presentation.userRepositories.adapter.UserRepositoryListAdapter
import br.com.gms.github.presentation.userRepositories.viewmodel.UserRepositoryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserRepositoryListFragment : Fragment() {

    private val args by navArgs<UserRepositoryListFragmentArgs>()
    private lateinit var binding: FragmentUserRepositoryListBinding
    private val viewModel by viewModels<UserRepositoryListViewModel>()
    private val navController: NavController by lazy { findNavController() }
    private val repositoryListAdapter: UserRepositoryListAdapter by lazy { UserRepositoryListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUserRepositories()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserRepositoryListBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setActions()
        setObservables()
    }

    private fun setView() {
        with(binding.layoutRepositoryList) {
            recyclerView.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                adapter = repositoryListAdapter.run {
                    withLoadStateHeaderAndFooter(
                        header = PagingLoadStateAdapter(this),
                        footer = PagingLoadStateAdapter(this)
                    )
                }
            }
        }
    }

    private fun setActions() {
        repositoryListAdapter.apply {
            addOnItemClickListener {
                //TODO - Implements
            }
        }
    }

    private fun setObservables() {
        repositoryListAdapter.apply {
            addLoadStateListener {
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

    private fun loadUserRepositories() {
        viewModel.loadUserRepositories(username = args.login)
    }

    private fun setLoadingLayout() {
        binding.apply {
            layoutError.root.isVisible = false
            layoutEmpty.root.isVisible = false
            layoutLoading.root.isVisible = true
            layoutRepositoryList.root.isVisible = false
        }
    }

    private fun setSuccessLayout(itemCount: Int) {
        binding.apply {
            layoutError.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutEmpty.root.isVisible = itemCount <= 0
            layoutRepositoryList.apply {
                root.isVisible = itemCount > 0
                textViewName.text = args.name
                textViewLogin.text = args.login
                imageViewUser.loadImageByUrl(args.avatarUrl)
            }
        }
    }

    private fun setFailureLayout(state: LoadState.Error) {
        binding.apply {
            layoutError.root.isVisible = true
            layoutEmpty.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutRepositoryList.root.isVisible = false
            layoutError.apply {
                root.isVisible = true
                state.error.message?.let { error ->
                    binding.layoutError.textViewDescription.text = error
                }
            }
        }
    }
}