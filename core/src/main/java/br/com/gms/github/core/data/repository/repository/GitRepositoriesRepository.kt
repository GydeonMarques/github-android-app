package br.com.gms.github.core.data.repository.repository

import androidx.paging.PagingData
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import kotlinx.coroutines.flow.Flow

interface GitRepositoriesRepository {
    suspend fun getUserRepositories(request: GitUserRepositoryRequest): Flow<PagingData<GitRepositoryDomain>>
}