package br.com.gms.github.core.data.repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.core.data.api.GitApiServices
import br.com.gms.github.core.data.models.request.UserRepositoryPullRequest
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest
import br.com.gms.github.core.data.pagging.GitRepositoryListPagingDataSource
import br.com.gms.github.core.data.pagging.GitPullRequestPagingDataSource
import br.com.gms.github.core.domain.model.GitPullRequestDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepositoriesRepositoryImpl @Inject constructor(
    private val service: GitApiServices,
) : GitRepositoriesRepository {
    override suspend fun getUserRepositories(request: GitUserRepositoryRequest): Flow<PagingData<GitRepositoryDomain>> {
        return Pager(
            initialKey = request.initialPage,
            config = PagingConfig(
                pageSize = request.pageSize,
                prefetchDistance = request.prefectDistance
            ),
            pagingSourceFactory = {
                GitRepositoryListPagingDataSource(
                    service,
                    request
                )
            }
        ).flow
    }

    override suspend fun getUserRepositoryPullRequest(request: UserRepositoryPullRequest): Flow<PagingData<GitPullRequestDomain>> {
        return Pager(
            initialKey = request.initialPage,
            config = PagingConfig(
                pageSize = request.pageSize,
                prefetchDistance = request.prefectDistance
            ),
            pagingSourceFactory = {
                GitPullRequestPagingDataSource(
                    service,
                    request
                )
            }
        ).flow
    }
}