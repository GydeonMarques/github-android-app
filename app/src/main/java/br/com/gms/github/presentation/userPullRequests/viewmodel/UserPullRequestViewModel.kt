package br.com.gms.github.presentation.userPullRequests.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.gms.github.core.data.models.request.UserRepositoryPullRequest
import br.com.gms.github.core.domain.model.GitPullRequestDomain
import br.com.gms.github.core.domain.usecase.repositories.GitRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserPullRequestViewModel @Inject constructor(
    private val useCase: GitRepositoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<GitPullRequestDomain>>(PagingData.empty())
    val state: Flow<PagingData<GitPullRequestDomain>> get() = _state

    fun getUserRepositoryPullRequest(username: String, repositoryName: String) {
        val request = UserRepositoryPullRequest(
            username = username,
            repositoryName = repositoryName
        )
        viewModelScope.launch {
            useCase.getUserRepositoryPullRequest(request)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _state.value = it
                }
        }
    }
}