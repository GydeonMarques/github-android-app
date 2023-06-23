package br.com.gms.github.core.data.api

import br.com.gms.github.core.data.models.response.GitPageResponse
import br.com.gms.github.core.data.model.response.GitPullRequestResponseTest
import br.com.gms.github.core.data.model.response.GitRepositoryResponseTest
import br.com.gms.github.core.data.model.response.GitUserResponseTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
internal class GitApiServicesTest {

    private val service: GitApiServices = mockk()

    @Test
    fun `WHEN fetching the list of users, the API THEN should return SUCCESS`() {
        runTest {
            //given
            val expectResponse = Response.success(
                GitPageResponse(
                    totalCount = 0,
                    items = GitUserResponseTest.mockUserResponseList
                )
            )

            //when
            coEvery {
                service.loadGitUsers(
                    query = "",
                    page = "",
                    pageSize = "",
                    sort = "",
                )
            } returns expectResponse
            val response = service.loadGitUsers(
                query = "",
                page = "",
                pageSize = "",
                sort = "",
            )

            //then
            assertTrue(response.isSuccessful)
            assertEquals(expectResponse.body(), response.body())
        }
    }

    @Test
    fun `WHEN fetching user information, the API THEN should return SUCCESS`() {
        runTest {
            //given

            val expectResponse = Response.success(
                GitUserResponseTest.mockUserResponse
            )

            //when
            coEvery {
                service.loadUserInfo(
                    username = ""
                )
            } returns expectResponse
            val response = service.loadUserInfo(
                username = ""
            )

            //then
            assertTrue(response.isSuccessful)
            assertEquals(expectResponse.body(), response.body())
        }
    }

    @Test
    fun `WHEN fetching the user's public repositories the API THEN should return SUCCESS`() {
        runTest {
            //given

            val expectResponse = Response.success(
                GitRepositoryResponseTest.mockGitRepositoryResponseList
            )

            //when
            coEvery {
                service.loadUserRepositories(
                    username = "",
                    page = "",
                    pageSize = "",
                    sort = "",
                )
            } returns expectResponse
            val response = service.loadUserRepositories(
                username = "",
                page = "",
                pageSize = "",
                sort = "",
            )

            //then
            assertTrue(response.isSuccessful)
            assertEquals(expectResponse.body(), response.body())
        }
    }

    @Test
    fun `WHEN fetching a pull request from the repository, the API THEN should return SUCCESS`() {
        runTest {
            //given

            val expectResponse = Response.success(
                GitPullRequestResponseTest.mockGitPullRequestResponseList
            )

            //when
            coEvery {
                service.loadAllPullsOfRepository(
                    username = "",
                    repositoryName = "",
                    query = "",
                    page = "",
                    pageSize = "",
                    sort = "",
                )
            } returns expectResponse
            val response = service.loadAllPullsOfRepository(
                username = "",
                repositoryName = "",
                query = "",
                page = "",
                pageSize = "",
                sort = "",
            )

            //then
            assertTrue(response.isSuccessful)
            assertEquals(expectResponse.body(), response.body())
        }
    }

}