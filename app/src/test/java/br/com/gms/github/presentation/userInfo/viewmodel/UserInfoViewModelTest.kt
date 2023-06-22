package br.com.gms.github.presentation.userInfo.viewmodel

import app.cash.turbine.test
import br.com.gms.github.core.data.models.response.Result
import br.com.gms.github.core.domain.model.GitUserDomain
import br.com.gms.github.core.domain.usecase.user.GitUsersUseCase
import br.com.gms.github.presentation.domain.model.GitUserDomainTest
import br.com.gms.github.presentation.base.CoroutinesRulesTest
import br.com.gms.github.presentation.userInfo.fragment.UserInfoUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any

@OptIn(ExperimentalCoroutinesApi::class)
class UserInfoViewModelTest {


    private var useCase: GitUsersUseCase = mockk()
    private lateinit var viewModel: UserInfoViewModel

    @get:Rule
    val rules: CoroutinesRulesTest = CoroutinesRulesTest()

    @Before
    fun setup() {
        viewModel = UserInfoViewModel(useCase)
    }

    @Test
    fun `WHEN fetching user information THEN it should return SUCCESS`() = runTest {
        //Given
        val username = ""
        val expectedResult = Result.Success(data = GitUserDomainTest.mockUserDomain)
        val expectedUiState = UserInfoUiState.Success(data = GitUserDomainTest.mockUserDomain)

        //When
        coEvery { useCase.getUserInfo(username) } returns expectedResult
        viewModel.loadUserInfo(username)

        //Then
        viewModel.uiSate.test {
            assertEquals(expectedUiState, awaitItem())
        }
    }

    @Test
    fun `WHEN fetching user information THEN it should return FAILURE`() = runTest {

        //Given
        val username = ""
        val errorMessage = ""
        val expectedResult = Result.Error<GitUserDomain>(message = errorMessage)
        val expectedUiState = UserInfoUiState.Failure(error = errorMessage)

        //When
        coEvery { useCase.getUserInfo(username) } returns expectedResult
        viewModel.loadUserInfo(username)

        //Then
        viewModel.uiSate.test {
            assertEquals(expectedUiState, awaitItem())
        }
    }
}