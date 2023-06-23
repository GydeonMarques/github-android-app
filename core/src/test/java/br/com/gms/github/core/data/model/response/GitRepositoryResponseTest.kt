package br.com.gms.github.core.data.model.response

import br.com.gms.github.core.data.models.response.GitRepositoryResponse

internal object GitRepositoryResponseTest {


    private val mockGitRepositoryResponse = GitRepositoryResponse(
        id = 1L,
        name = "",
        fullName = "",
        forksCount = 0L,
        description = "",
        watchersCount = 0L,
        owner = GitUserResponseTest.mockUserResponse
    )

    val mockGitRepositoryResponseList = arrayListOf<GitRepositoryResponse>().apply {
        (0..10).forEach { add(mockGitRepositoryResponse.copy(id = it.toLong())) }
    }.toList()
}