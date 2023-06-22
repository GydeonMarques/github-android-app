package br.com.gms.github.core.domain.model

data class GitRepositoryDomain(
    val id: Long,
    val name: String,
    val fullName: String,
    val forksCount: Long,
    val description: String,
    val watchersCount: Long,
    val owner: GitUserDomain
)