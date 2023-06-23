package br.com.gms.github.core.data.model.response

import br.com.gms.github.core.data.models.response.GitPullRequestResponse

internal object GitPullRequestResponseTest {

    private val mockGitPullRequestResponse = GitPullRequestResponse(
        id = 1L,
        body = "Body",
        title = "Title",
        number = 1L,
        pullURL = "Pull URL",
        createdAt = "Created At",
        user = GitUserResponseTest.mockUserResponse,
    )

    val mockGitPullRequestResponseList = arrayListOf<GitPullRequestResponse>().apply {
        (0..5).forEach { add(mockGitPullRequestResponse.copy(id = it.toLong())) }
    }.toList()
}