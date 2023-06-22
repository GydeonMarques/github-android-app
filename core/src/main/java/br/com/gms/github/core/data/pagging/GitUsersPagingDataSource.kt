package br.com.gms.github.core.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.gms.github.core.util.paging.PageParamsRequest
import br.com.gms.github.core.data.api.GitApiServices
import br.com.gms.github.core.data.mapper.toModel
import br.com.gms.github.core.data.models.response.GitErrorResponse
import br.com.gms.github.core.domain.model.GitUserDomain
import com.fasterxml.jackson.databind.ObjectMapper


internal class GitUsersPagingDataSource(
    private val service: GitApiServices,
    private val request: PageParamsRequest
) : PagingSource<Long, GitUserDomain>() {

    override fun getRefreshKey(state: PagingState<Long, GitUserDomain>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, GitUserDomain> {
        return try {

            val currentPage = params.key ?: request.initialPage

            val response = service.loadGitUsers(
                query = request.query,
                sort = request.sortBy,
                page = currentPage.toString(),
                pageSize = request.pageSize.toString(),
            )

            when {
                response.body() != null -> {
                    LoadResult.Page(
                        data = response.body()?.items?.map { it.toModel() } ?: emptyList(),
                        prevKey = if (currentPage <= request.initialPage) null else currentPage - 1,
                        nextKey = if (response.body()?.items?.isEmpty() == true) null else currentPage + 1,
                    )
                }

                response.errorBody() != null -> {
                    ObjectMapper().readValue(response.errorBody()?.string(), GitErrorResponse::class.java).run {
                        LoadResult.Error(Exception(this.message))
                    }
                }

                else -> {
                    LoadResult.Error(Exception("Ocorreu um erro ao carregar os dados."))
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}