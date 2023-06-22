package br.com.gms.github.core.data.models.response

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class GitErrorResponse(
    @JsonProperty("message") val message: String
) : Parcelable