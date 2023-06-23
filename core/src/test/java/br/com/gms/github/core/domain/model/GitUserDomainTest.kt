package br.com.gms.github.core.domain.model


object GitUserDomainTest {

    val mockUserDomain = GitUserDomain(
        id = 1L,
        name = "",
        blog = "",
        email = "",
        login = "",
        location = "",
        biography = "",
        avatarUrl = "",
    )

    val mockUserDomainList = arrayListOf<GitUserDomain>().apply {
        (0..10).forEach { add(mockUserDomain.copy(id = it.toLong())) }
    }
}