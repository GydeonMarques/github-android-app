package br.com.gms.github.presentation.userInfo.fragment

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
import br.com.gms.github.core.util.image.loadImageByUrl
import br.com.gms.github.databinding.FragmentUserInfoBinding
import br.com.gms.github.presentation.userInfo.viewmodel.UserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private val args by navArgs<UserInfoFragmentArgs>()
    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel by viewModels<UserInfoViewModel>()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserInfoBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModel.loadUserInfo(args.username)
    }

    private fun setObservables() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiSate.collect { uiSate ->
                when (uiSate) {
                    is UserInfoUiState.Loading -> setLoadingLayout()
                    is UserInfoUiState.Failure -> setFailureLayout(uiSate)
                    is UserInfoUiState.Success -> setSuccessLayout(uiSate)
                }
            }
        }
    }

    private fun setLoadingLayout() {
        binding.apply {
            layoutError.root.isVisible = false
            layoutLoading.root.isVisible = true
            layoutUserInfo.root.isVisible = false
        }
    }

    private fun setSuccessLayout(state: UserInfoUiState.Success) {
        binding.apply {
            layoutError.root.isVisible = false
            layoutLoading.root.isVisible = false
            layoutUserInfo.root.isVisible = true
        }

        with(state) {
            binding.layoutUserInfo.apply {
                imageViewUser.loadImageByUrl(data.avatarUrl)
                textViewName.text = data.name
                textViewLogin.text = data.login

                textViewBlog.apply {
                    text = data.blog
                    isVisible = data.blog.isNotEmpty()
                }

                textViewEmail.apply {
                    text = data.email
                    isVisible = data.email.isNotEmpty()
                }

                textViewBio.apply {
                    text = data.biography
                    isVisible = data.biography.isNotEmpty()
                }

                textViewLocation.apply {
                    text = data.location
                    isVisible = data.location.isNotEmpty()
                }

                buttonViewRepositories.setOnClickListener {
                    navController.navigate(
                        UserInfoFragmentDirections.goToUserRepositories(
                            data.name,
                            data.login,
                            data.avatarUrl
                        )
                    )
                }
            }
        }
    }

    private fun setFailureLayout(state: UserInfoUiState.Failure) {
        binding.apply {
            layoutError.root.isVisible = true
            layoutLoading.root.isVisible = false
            layoutUserInfo.root.isVisible = false
            layoutError.apply {
                root.isVisible = true
                binding.layoutError.textViewDescription.text = state.error
            }
        }
    }
}