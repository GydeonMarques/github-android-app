package br.com.gms.github.core.data.models.response

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(
        var code: String? = null,
        var message: String? = null,
        val exception: Exception? = null
    ) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Error -> "Error[code=$code, message=$message, exception$exception]"
        }
    }
}