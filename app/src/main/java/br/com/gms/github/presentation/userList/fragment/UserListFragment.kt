package br.com.gms.github.presentation.userList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.com.gms.github.core.util.paging.PagingLoadStateAdapter
import br.com.gms.github.databinding.FragmentUserListBinding
import br.com.gms.github.presentation.userList.adapter.UserListAdapter
import br.com.gms.github.presentation.userList.viewmodel.EMPTY
import br.com.gms.github.presentation.userList.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var isSearching = false
    private var lastSearchedText: String? = null
    private lateinit var binding: FragmentUserListBinding
    private val viewModel by viewModels<UserListViewModel>()
    private val navController: NavController by lazy { findNavController() }
    private val gitUsersAdapter: UserListAdapter by lazy { UserListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false).run {
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

    private fun loadGitUsers(query: String = EMPTY) {
        viewModel.loadGitUsers(query = query)
    }

    private fun setView() {
        with(binding.layoutUserList) {
            recyclerView.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                adapter = gitUsersAdapter.run {
                    withLoadStateHeaderAndFooter(
                        header = PagingLoadStateAdapter(this),
                        footer = PagingLoadStateAdapter(this)
                    )
                }
            }
        }
    }

    private fun setActions() {
        binding.apply {
            editTextSearch.doAfterTextChanged {
                it.toString().let { text ->
                    when {
                        text.isNotEmpty() && text != lastSearchedText -> {
                            isSearching = true
                            loadGitUsers(text)
                            lastSearchedText = text
                        }

                        isSearching -> {
                            lastSearchedText = null
                            isSearching = false
                            loadGitUsers()
                        }
                    }
                }
            }

            gitUsersAdapter.addOnItemClickListener { model ->
                navController.navigate(
                    UserListFragmentDirections.goToUserInfo(
                        model.login
                    )
                )
            }
        }
    }

    private fun setObservables() {
        gitUsersAdapter.apply {
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

    private fun setLoadingLayout() {
        binding.apply {
            layoutEmpty.root.isVisible = false
            layoutError.root.isVisible = false
            layoutLoading.root.isVisible = true
            layoutUserList.root.isVisible = false
            textInputSearch.isVisible = isSearching
        }
    }

    private fun setSuccessLayout(itemCount: Int) {
        binding.apply {
            textInputSearch.isVisible = true
            layoutError.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutEmpty.root.isVisible = itemCount <= 0
            layoutUserList.root.isVisible = itemCount > 0
        }
    }

    private fun setFailureLayout(state: LoadState.Error) {
        binding.apply {
            layoutEmpty.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutUserList.root.isVisible = false
            textInputSearch.isVisible = isSearching
            layoutError.apply {
                root.isVisible = true
                state.error.message?.let { error ->
                    binding.layoutError.textViewDescription.text = error
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isSearching = false
    }
}