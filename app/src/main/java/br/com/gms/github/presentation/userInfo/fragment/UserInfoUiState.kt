package br.com.gms.github.presentation.userInfo.fragment

import br.com.gms.github.core.domain.model.GitUserDomain


sealed class UserInfoUiState {
    object Loading : UserInfoUiState()
    data class Failure(val error: String) : UserInfoUiState()
    data class Success(val data: GitUserDomain) : UserInfoUiState()
}