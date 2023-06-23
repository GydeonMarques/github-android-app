package br.com.gms.github.core.data.pagging

import br.com.gms.github.core.data.model.response.GitRepositoryPagingDataSourceTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class GitRepositoryListPagingDataSourceTest {

    private var pagingDataSource: GitRepositoryListPagingDataSource = mockk()

    @Test
    fun `WHEN load is calling to get user repositories THEN it should return ERROR`() {
        runTest {

            //given
            val mockParams = GitRepositoryPagingDataSourceTest.mockLoadParams
            val expectedResponse = GitRepositoryPagingDataSourceTest.mockPageResultFailure

            //when
            coEvery { pagingDataSource.load(mockParams) } returns expectedResponse
            val result = pagingDataSource.load(mockParams)

            //then
            assertEquals(expectedResponse, result)
        }
    }

    @Test
    fun `WHEN load is calling to get user repositories THEN it should return SUCCESS`() {
        runTest {

            //given
            val mockParams = GitRepositoryPagingDataSourceTest.mockLoadParams
            val expectedResponse = GitRepositoryPagingDataSourceTest.mockPageResultSuccess

            //when
            coEvery { pagingDataSource.load(mockParams) } returns expectedResponse
            val result = pagingDataSource.load(mockParams)

            //then
            assertEquals(expectedResponse, result)
        }
    }
}