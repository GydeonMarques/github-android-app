package br.com.gms.github.presentation.userRepositories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest
import br.com.gms.github.core.domain.usecase.repositories.GitRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserRepositoryListViewModel @Inject constructor(
    private val useCase: GitRepositoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<GitRepositoryDomain>>(PagingData.empty())
    val state: Flow<PagingData<GitRepositoryDomain>> get() = _state

    fun loadUserRepositories(username: String) {
        viewModelScope.launch {
            useCase.getUserRepositories(GitUserRepositoryRequest(username = username))
                .cachedIn(viewModelScope)
                .collectLatest {
                    _state.value = it
                }
        }
    }
}