package br.com.gms.github.core.domain.model

object GitPullRequestDomainTest {

    private val mockGitPullRequestDomain = GitPullRequestDomain(
        id = 0,
        body = "",
        title = "",
        number = 0,
        pullURL = "",
        createdAt = "",
        user = GitUserDomainTest.mockUserDomain,
    )

    val mockGitPullRequestDomainList = arrayListOf<GitPullRequestDomain>().apply {
        (0..10).forEach { add(mockGitPullRequestDomain.copy(id = it.toLong())) }
    }.toList()
}