package br.com.gms.github.core.domain.usecase.user

import androidx.paging.PagingData
import br.com.gms.github.core.data.models.response.Result
import br.com.gms.github.core.data.repository.user.GitUsersRepository
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.core.domain.model.GitUserDomainTest
import br.com.gms.github.core.util.paging.PageParamsRequest
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
class GitUsersUseCaseImplTest {


    private val repository: GitUsersRepository = mockk()
    private lateinit var useCase: GitUsersUseCase

    @Before
    fun setup() {
        useCase = GitUsersUseCaseImpl(repository);
    }


    @Test
    fun `WHEN get list of users THEN should return list of users with PAGINATION`() {
        runTest {

            //given
            val pageRequest = PageParamsRequest()
            val expectedResponse = flowOf(PagingData.from(GitUserDomainTest.mockUserDomainList))

            //when
            coEvery { useCase.getUsers(pageRequest) } returns expectedResponse
            val result = useCase.getUsers(pageRequest)

            //then
            coVerify { repository.getUsers(pageRequest) }
            Assert.assertEquals(expectedResponse, result)
        }
    }

    @Test
    fun `WHEN get user info, THEN should return SUCCESS`() {
        runTest {

            //given
            val mockRequest = "username"
            val expectedResponse = Result.Success(data = GitUserDomainTest.mockUserDomain)

            //when
            coEvery { useCase.getUserInfo(mockRequest) } returns expectedResponse
            val result = useCase.getUserInfo(mockRequest)

            //then
            Assert.assertEquals(expectedResponse, result)
            coVerify { repository.getUserInfo(mockRequest) }
        }
    }

    @Test
    fun `WHEN get user info, THEN should return ERROR`() {
        runTest {

            //given
            val mockRequest = "username"
            val expectedResponse = Result.Error<GitUserDomain>(
                code = "500",
                message = "Internal server error",
                exception = Exception("Internal server error")
            )

            //when
            coEvery { useCase.getUserInfo(mockRequest) } returns expectedResponse
            val result = useCase.getUserInfo(mockRequest)

            //then
            coVerify { repository.getUserInfo(mockRequest) }
            Assert.assertEquals(expectedResponse, result)
        }
    }
}