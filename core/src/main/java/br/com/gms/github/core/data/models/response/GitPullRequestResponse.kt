package br.com.gms.github.core.data.models.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitPullRequestResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("body") val body: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("number") val number: Long?,
    @JsonProperty("html_url") val pullURL: String?,
    @JsonProperty("created_at") val createdAt: String?,
    @JsonProperty("user") val user: GitUserResponse,
)