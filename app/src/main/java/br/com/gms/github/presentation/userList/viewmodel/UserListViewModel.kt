package br.com.gms.github.presentation.userList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.gms.github.core.util.paging.PageParamsRequest
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.core.domain.usecase.user.GitUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

internal const val EMPTY = "\"\""

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    private val useCase: GitUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<GitUserDomain>>(PagingData.empty())
    val state: Flow<PagingData<GitUserDomain>> get() = _state
    private var loadUserJob: Job? = null

    init {
        loadGitUsers()
    }

    fun loadGitUsers(query: String = EMPTY) {
        loadUserJob?.cancel()
        loadUserJob = viewModelScope.launch {
            if (query != EMPTY) delay(500)
            useCase.getUsers(PageParamsRequest(query = query))
                .cachedIn(viewModelScope)
                .collectLatest {
                    _state.value = it
                }
        }
    }
}