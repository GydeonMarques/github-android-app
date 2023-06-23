package br.com.gms.github.core.data.model.response

import androidx.paging.PagingSource
import br.com.gms.github.core.domain.model.GitRepositoryDomain
import br.com.gms.github.core.domain.model.GitRepositoryDomainTest

internal object GitRepositoryPagingDataSourceTest {

    val mockLoadParams = PagingSource.LoadParams.Refresh(
        key = 1L,
        loadSize = 10,
        placeholdersEnabled = false
    )

    val mockPageResultSuccess = PagingSource.LoadResult.Page<Long, GitRepositoryDomain>(
        data = GitRepositoryDomainTest.mockGitRepositoryDomainList,
        prevKey = 0,
        nextKey = 1
    )

    val mockPageResultFailure = PagingSource.LoadResult.Error<Long, GitRepositoryDomain>(
        Exception("Ocorreu um erro ao carregar os dados.")
    )

}