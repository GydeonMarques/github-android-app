package br.com.gms.github.core.domain.usecase.user

import androidx.paging.PagingData
import br.com.gms.github.core.util.paging.PageParamsRequest
import br.com.gms.github.core.data.models.response.Result
import br.com.gms.github.core.domain.model.GitUserDomain
import kotlinx.coroutines.flow.Flow

interface GitUsersUseCase {
    suspend fun getUsers(request: PageParamsRequest): Flow<PagingData<GitUserDomain>>
    suspend fun getUserInfo(username: String): Result<GitUserDomain>
}