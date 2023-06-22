package br.com.gms.github.core.data.mapper

import br.com.gms.github.core.data.models.response.GitRepositoryResponse
import br.com.gms.github.core.domain.model.GitRepositoryDomain

fun GitRepositoryResponse.toModel(): GitRepositoryDomain {
    return GitRepositoryDomain(
        id = id ?: 0,
        name = name ?: "",
        fullName = fullName ?: "",
        forksCount = forksCount ?: 0,
        description = description ?: "",
        watchersCount = watchersCount ?: 0,
        owner = owner.toModel()
    )
}