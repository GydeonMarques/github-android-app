package br.com.gms.github.core.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.core.data.api.GitApiServices
import br.com.gms.github.core.data.mapper.toModel
import br.com.gms.github.core.data.models.request.GitUserRepositoryRequest

internal class GitRepositoryListPagingDataSource(
    private val service: GitApiServices,
    private val request: GitUserRepositoryRequest,
) : PagingSource<Long, GitRepositoryDomain>() {

    override fun getRefreshKey(state: PagingState<Long, GitRepositoryDomain>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, GitRepositoryDomain> {
        return try {

            val currentPage = params.key ?: request.initialPage

            val response = service.loadUserRepositories(
                sort = request.sortBy,
                username = request.username,
                page = currentPage.toString(),
                pageSize = request.pageSize.toString()
            )

            when {
                response.body() != null -> {
                    LoadResult.Page(
                        data = response.body()?.map { it.toModel() } ?: emptyList(),
                        prevKey = if (currentPage <= request.initialPage) null else currentPage - 1,
                        nextKey = if (response.body()?.isEmpty() == true) null else currentPage + 1
                    )
                }

                response.errorBody() != null -> {
                    LoadResult.Error(Exception(response.errorBody()?.string()))
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