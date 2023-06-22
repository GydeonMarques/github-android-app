package br.com.gms.github.core.domain.model

data class GitPullRequestDomain(
    val id: Long,
    val title: String,
    val body: String,
    val number: Long,
    val pullURL: String?,
    val createdAt: String,
    val user: GitUserDomain,
)