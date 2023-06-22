package br.com.gms.github.core.data.repository.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.gms.github.core.util.paging.PageParamsRequest
import br.com.gms.github.core.data.api.GitApiServices
import br.com.gms.github.core.data.mapper.toModel
import br.com.gms.github.core.data.models.response.GitErrorResponse
import br.com.gms.github.core.data.models.response.Result
import br.com.gms.github.core.data.pagging.GitUsersPagingDataSource
import br.com.gms.github.core.domain.model.GitUserDomain
import com.fasterxml.jackson.databind.ObjectMapper
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

    override suspend fun getUserInfo(username: String): Result<GitUserDomain> {
        return try {
            val response = service.loadUserInfo(username)
            when {
                response.body() != null -> Result.Success(
                    data = response.body()!!.toModel()
                )

                response.errorBody() != null -> Result.Error(
                    code = response.code().toString(),
                    message = ObjectMapper().readValue(
                        response.errorBody()?.string(),
                        GitErrorResponse::class.java
                    ).message
                )

                else -> Result.Error(
                    message = "Ocorreu um erro ao carregar os dados."
                )
            }
        } catch (e: Exception) {
            Result.Error(message = e.message)
        }
    }
}