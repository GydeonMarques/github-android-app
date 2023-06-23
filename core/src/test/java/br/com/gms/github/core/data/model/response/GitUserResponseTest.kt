package br.com.gms.github.core.data.model.response

import br.com.gms.github.core.data.models.response.GitUserResponse


internal object GitUserResponseTest {

    val mockUserResponse = GitUserResponse(
        id = 1L,
        name = "",
        blog = "",
        email = "",
        login = "",
        location = "",
        biography = "",
        avatarUrl = "",
    )

    val mockUserResponseList = arrayListOf<GitUserResponse>().apply {
        (0..10).forEach { add(mockUserResponse.copy(id = it.toLong())) }
    }.toList()
}