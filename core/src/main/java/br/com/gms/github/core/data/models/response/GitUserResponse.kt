package br.com.gms.github.core.data.models.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitUserResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("blog") val blog: String?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("login") val login: String?,
    @JsonProperty("bio") val biography: String?,
    @JsonProperty("location") val location: String?,
    @JsonProperty("avatar_url") val avatarUrl: String?,
)
