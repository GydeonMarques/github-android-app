package br.com.gms.github.core.data.models.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitPageResponse<T>(
    @JsonProperty("items") val items: List<T>?,
    @JsonProperty("total_count") val totalCount: Long?
)