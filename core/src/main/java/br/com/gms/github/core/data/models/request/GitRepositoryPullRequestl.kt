package br.com.gms.github.core.data.models.request

import br.com.gms.github.core.util.paging.PageParamsRequest


data class UserRepositoryPullRequest(
    val username: String,
    val repositoryName: String,
) : PageParamsRequest()
