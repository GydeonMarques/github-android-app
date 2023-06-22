package br.com.gms.github.core.data.repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.gms.github.core.data.api.GitApiServices
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest
import br.com.gms.github.core.data.pagging.GitRepositoryListPagingDataSource
import br.com.gms.github.core.domain.model.GitRepositoryDomain
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
}