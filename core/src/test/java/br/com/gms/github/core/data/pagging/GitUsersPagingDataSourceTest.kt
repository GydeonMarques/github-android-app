package br.com.gms.github.core.data.pagging

import br.com.gms.github.core.data.model.response.GitUserPagingDataSourceTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class GitUsersPagingDataSourceTest {

    private var pagingDataSource: GitUsersPagingDataSource = mockk()

    @Test
    fun `WHEN load is calling to get the list of users THEN it should return ERROR`() {
        runTest {

            //given
            val mockParams = GitUserPagingDataSourceTest.mockLoadParams
            val expectedResponse = GitUserPagingDataSourceTest.mockPageResultFailure

            //when
            coEvery { pagingDataSource.load(mockParams) } returns expectedResponse
            val result = pagingDataSource.load(mockParams)

            //then
            assertEquals(expectedResponse, result)
        }
    }

    @Test
    fun `WHEN load is calling to get the list of users THEN it should return SUCCESS`() {
        runTest {

            //given
            val mockParams = GitUserPagingDataSourceTest.mockLoadParams
            val expectedResponse = GitUserPagingDataSourceTest.mockPageResultSuccess

            //when
            coEvery { pagingDataSource.load(mockParams) } returns expectedResponse
            val result = pagingDataSource.load(mockParams)

            //then
            assertEquals(expectedResponse, result)
        }
    }
}