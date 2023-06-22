package br.com.gms.github.core.domain.usecase.repositories

import androidx.paging.PagingData
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest
import br.com.gms.github.core.data.models.request.UserRepositoryPullRequest
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.core.domain.model.GitPullRequestDomain
import kotlinx.coroutines.flow.Flow

interface GitRepositoriesUseCase {
    suspend fun getUserRepositories(request: GitUserRepositoryRequest): Flow<PagingData<GitRepositoryDomain>>
    suspend fun getUserRepositoryPullRequest(request: UserRepositoryPullRequest): Flow<PagingData<GitPullRequestDomain>>
}