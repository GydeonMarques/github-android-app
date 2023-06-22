package br.com.gms.github.core.data.mapper

import br.com.gms.github.core.data.models.response.GitUserResponse
import br.com.gms.github.core.domain.model.GitUserDomain
fun GitUserResponse.toModel(): GitUserDomain {
    return GitUserDomain(
        id = id ?: 0,
        name = name ?: "",
        blog = blog ?: "",
        email = email ?: "",
        login = login ?: "",
        location = location ?: "",
        biography = biography ?: "",
        avatarUrl = avatarUrl ?: "",
    )
}