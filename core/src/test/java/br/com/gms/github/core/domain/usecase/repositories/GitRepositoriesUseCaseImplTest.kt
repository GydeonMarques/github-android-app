package br.com.gms.github.core.domain.usecase.repositories

import androidx.paging.PagingData
import br.com.gms.github.core.data.model.request.GitUserRepositoryRequestTest
import br.com.gms.github.core.data.model.request.UserRepositoryPullRequestTest
import br.com.gms.github.core.data.repository.repository.GitRepositoriesRepository
import br.com.gms.github.core.domain.model.GitPullRequestDomainTest
import br.com.gms.github.core.domain.model.GitRepositoryDomainTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitRepositoriesUseCaseImplTest {

    private val repository: GitRepositoriesRepository = mockk()
    private lateinit var useCase: GitRepositoriesUseCase

    @Before
    fun setup() {
        useCase = GitRepositoriesUseCaseImpl(repository)
    }

    @Test
    fun `WHEN get list of repositories from user THEN should return list of repositories with PAGINATION`() {
        runTest {

            //given
            val mockRequest = GitUserRepositoryRequestTest.mockRequest
            val expectedResponse = flowOf(PagingData.from(GitRepositoryDomainTest.mockGitRepositoryDomainList))

            //when
            coEvery { useCase.getUserRepositories(mockRequest) } returns expectedResponse
            val result = useCase.getUserRepositories(mockRequest)

            //then
            coVerify { repository.getUserRepositories(mockRequest) }
            Assert.assertEquals(expectedResponse, result)
        }
    }

    @Test
    fun `WHEN get list of pull requests from a repository, THEN should return from pull requests with PAGINATION`() {
        runTest {

            //given
            val mockRequest = UserRepositoryPullRequestTest.mockRequest
            val expectedResponse = flowOf(PagingData.from(GitPullRequestDomainTest.mockGitPullRequestDomainList))

            //when
            coEvery { useCase.getUserRepositoryPullRequest(mockRequest) } returns expectedResponse
            val result = useCase.getUserRepositoryPullRequest(mockRequest)

            //then
            coVerify { repository.getUserRepositoryPullRequest(mockRequest) }
            Assert.assertEquals(expectedResponse, result)
        }
    }
}