package br.com.gms.github.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitUserDomain(
    val id: Long,
    val blog: String,
    val name: String,
    val email: String,
    val login: String,
    val location: String,
    val biography: String,
    val avatarUrl: String,
) : Parcelable