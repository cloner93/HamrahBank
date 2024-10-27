package com.pmb.core.platform

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val exception: Throwable? = null) : Result<Nothing>()
    data object Loading : Result<Nothing>()

    /**
     * Transforms the successful data of type T to a new type R.
     */
    fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(message, exception)
        is Loading -> Loading
    }

    /**
     * Applies the given action to the successful data if this is a Success instance.
     */
    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success) action(data)
        return this
    }

    /**
     * Applies the given action if this is an Error instance.
     */
    inline fun onError(action: (String?, Throwable?) -> Unit): Result<T> {
        if (this is Error) action(message, exception)
        return this
    }

    /**
     * Applies the given action if this is a Loading instance.
     */
    inline fun onLoading(action: () -> Unit): Result<T> {
        if (this is Loading) action()
        return this
    }

    /**
     * Returns true if this is a Success instance.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Returns true if this is an Error instance.
     */
    val isError: Boolean get() = this is Error

    /**
     * Returns true if this is a Loading instance.
     */
    val isLoading: Boolean get() = this is Loading

    /**
     * Unwraps the success data or returns null if not a Success.
     */
    fun getOrNull(): T? = (this as? Success)?.data

    /**
     * Returns the success data or throws the exception if this is an Error.
     */
    fun getOrThrow(): T = when (this) {
        is Success -> data
        is Error -> throw exception ?: IllegalStateException(message ?: "An unknown error occurred")
        is Loading -> throw IllegalStateException("Cannot retrieve data in Loading state")
    }
}
