package br.com.gms.github.presentation.domain.model

import br.com.gms.github.core.domain.model.GitUserDomain


internal object GitUserDomainTest {

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
}