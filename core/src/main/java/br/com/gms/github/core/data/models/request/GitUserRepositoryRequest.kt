package br.com.gms.github.core.data.models.request

import br.com.gms.github.core.util.paging.PageParamsRequest


data class GitUserRepositoryRequest(
    val username: String,
) : PageParamsRequest()
