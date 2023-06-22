package br.com.gms.github.presentation.userInfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gms.github.core.data.models.response.Result
import br.com.gms.github.core.domain.usecase.user.GitUsersUseCase
import br.com.gms.github.presentation.userInfo.fragment.UserInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserInfoViewModel @Inject constructor(
    private val useCase: GitUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserInfoUiState>(UserInfoUiState.Loading)
    val uiSate: StateFlow<UserInfoUiState> get() = _uiState

    fun loadUserInfo(username: String) {
        viewModelScope.launch {
            when (val result = useCase.getUserInfo(username)) {
                is Result.Success -> _uiState.update {
                    UserInfoUiState.Success(data = result.data)
                }

                is Result.Error -> _uiState.update {
                    UserInfoUiState.Failure(
                        error = result.message ?: ""
                    )
                }
            }
        }
    }
}