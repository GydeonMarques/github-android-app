package br.com.gms.github.core.domain.model

object GitRepositoryDomainTest {


    private val mockGitRepositoryDomain = GitRepositoryDomain(
        id = 1L,
        name = "",
        fullName = "",
        forksCount = 0L,
        description = "",
        watchersCount = 0L,
        owner = GitUserDomainTest.mockUserDomain
    )

    val mockGitRepositoryDomainList = arrayListOf<GitRepositoryDomain>().apply {
        (0..10).forEach { add(mockGitRepositoryDomain.copy(id = it.toLong())) }
    }.toList()
}