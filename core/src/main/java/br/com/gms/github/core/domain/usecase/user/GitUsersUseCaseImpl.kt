package br.com.gms.github.core.domain.usecase.user

import androidx.paging.PagingData
import br.com.gms.github.core.data.repository.user.GitUsersRepository
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.core.util.paging.PageParamsRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitUsersUseCaseImpl @Inject constructor(
    private val repository: GitUsersRepository
) : GitUsersUseCase {
    override suspend fun getUsers(request: PageParamsRequest): Flow<PagingData<GitUserDomain>> {
        return repository.getUsers(request)
    }
}