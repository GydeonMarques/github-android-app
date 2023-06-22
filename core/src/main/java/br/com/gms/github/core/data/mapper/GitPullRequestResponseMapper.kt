package br.com.gms.github.core.data.mapper

import br.com.gms.github.core.data.models.response.GitPullRequestResponse
import br.com.gms.github.core.domain.model.GitPullRequestDomain

fun GitPullRequestResponse.toModel(): GitPullRequestDomain {
    return GitPullRequestDomain(
        id = id ?: 0L,
        title = title ?: "",
        body = body ?: "",
        number = number ?: 0,
        pullURL = pullURL ?: "",
        createdAt = createdAt ?: "",
        user = user.toModel()
    )
}