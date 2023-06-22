package br.com.gms.github.core.data.models.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitRepositoryResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("full_name") val fullName: String?,
    @JsonProperty("forks_count") val forksCount: Long?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("watchers_count") val watchersCount: Long?,
    @JsonProperty("owner") val owner: GitUserResponse
)