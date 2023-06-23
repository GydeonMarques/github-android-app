package br.com.gms.github.core.data.model.response

import androidx.paging.PagingSource
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.core.domain.model.GitUserDomainTest

internal object GitUserPagingDataSourceTest {

    val mockLoadParams = PagingSource.LoadParams.Refresh(
        key = 1L,
        loadSize = 10,
        placeholdersEnabled = false
    )

    val mockPageResultSuccess = PagingSource.LoadResult.Page<Long, GitUserDomain>(
        data = GitUserDomainTest.mockUserDomainList,
        prevKey = 0,
        nextKey = 1
    )

    val mockPageResultFailure = PagingSource.LoadResult.Error<Long, GitUserDomain>(
        Exception("Ocorreu um erro ao carregar os dados.")
    )

}