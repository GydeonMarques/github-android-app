package br.com.gms.github.core.data.repository.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.gms.github.core.data.api.GitApiServices
import br.com.gms.github.core.data.pagging.GitUsersPagingDataSource
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.core.util.paging.PageParamsRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitUsersRepositoryImpl @Inject constructor(
    private val service: GitApiServices,
) : GitUsersRepository {
    override suspend fun getUsers(request: PageParamsRequest): Flow<PagingData<GitUserDomain>> {
        return Pager(
            initialKey = request.initialPage,
            config = PagingConfig(
                pageSize = request.pageSize,
                prefetchDistance = request.prefectDistance
            ),
            pagingSourceFactory = {
                GitUsersPagingDataSource(
                    service,
                    request
                )
            }
        ).flow
    }
}