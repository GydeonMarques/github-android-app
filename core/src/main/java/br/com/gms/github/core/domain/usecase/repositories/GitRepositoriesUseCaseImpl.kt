package br.com.gms.github.core.domain.usecase.repositories

import androidx.paging.PagingData
import br.com.gms.github.core.data.models.request.UserRepositoryPullRequest
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest
import br.com.gms.github.core.data.repository.repository.GitRepositoriesRepository
import br.com.gms.github.core.domain.model.GitPullRequestDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepositoriesUseCaseImpl @Inject constructor(
    private val repository: GitRepositoriesRepository
) : GitRepositoriesUseCase {

    override suspend fun getUserRepositories(request: GitUserRepositoryRequest): Flow<PagingData<GitRepositoryDomain>> {
        return repository.getUserRepositories(request)
    }

    override suspend fun getUserRepositoryPullRequest(request: UserRepositoryPullRequest): Flow<PagingData<GitPullRequestDomain>> {
        return repository.getUserRepositoryPullRequest(request)
    }
}