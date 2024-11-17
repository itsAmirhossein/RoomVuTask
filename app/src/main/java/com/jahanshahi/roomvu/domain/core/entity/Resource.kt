package com.sample.domain.core.entity


import com.jahanshahi.roomvu.common.mapper.Mapper
import com.sample.domain.core.entity.Resource.Canceled
import com.sample.domain.core.entity.Resource.Companion.PUBLIC_CONNECTION_ERROR_MESSAGE
import com.sample.domain.core.entity.Resource.Companion.PUBLIC_ERROR_MESSAGE
import com.sample.domain.core.entity.Resource.Error
import com.sample.domain.core.entity.Resource.Loading
import com.sample.domain.core.entity.Resource.None
import com.sample.domain.core.entity.Resource.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.io.IOException

sealed class Resource<out R> {

    object None : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Exception) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
    object Canceled : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
            Canceled -> "Canceled"
            None -> "None"
        }
    }

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isNone() = this is None
    fun isError() = this is Error
    fun isCanceled() = this is Canceled

    companion object {
        const val PUBLIC_ERROR_MESSAGE = "An error has occurred. Please try again later."
        const val PUBLIC_CONNECTION_ERROR_MESSAGE = "Please check your internet connection."
    }
}

inline fun <R> Resource<R>.onLoading(action: (Boolean) -> Unit): Resource<R> {
    if (this is Loading) {
        action(this.loading)
    } else {
        action(false)
    }
    return this
}

inline fun <T> Resource<T>.onAnyError(onFailure: (Exception, String?) -> Unit) {
    if (this is Error) {
        val errorMessage = if (error.message.isNullOrEmpty().not())
            error.message
        else if (error.cause?.message.isNullOrEmpty().not())
            error.cause?.message
        else
            PUBLIC_ERROR_MESSAGE

        onFailure(error, errorMessage)
    }
}

inline fun <T> Resource<T>.onConnectionError(onFailure: (Exception, String?) -> Unit) {
    if (this is Error && (this.error.cause is IOException)) {
        val errorMessage = PUBLIC_CONNECTION_ERROR_MESSAGE
        onFailure(error, errorMessage)
    }
}

inline fun <R> Resource<R>.onSuccess(action: (R) -> Unit): Resource<R> {
    if (this is Success) {
        action(data)
    }
    return this
}

fun <R> Resource<R>.getDataOrException(): Success<R> {
    return (this as Success)
}

fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

fun <R, T> Resource<R>.map(mapper: Mapper<R, T>): Resource<T> {
    return when {
        this.isNone() -> {
            None
        }

        this.isSuccess() -> {
            Success(mapper.map((this as Success).data))
        }

        this.isLoading() -> {
            Loading(((this as Loading).loading))
        }

        this.isCanceled() -> {
            return Canceled
        }

        else -> {
            Error((this as Error).error)
        }
    }
}


suspend fun <R, T> Flow<Resource<R>>.flowMap(
    stateFlow: MutableStateFlow<Resource<T>>,
    mapper: Mapper<R, T>
) {
    collectLatest {
        stateFlow.value = it.map(mapper)
    }
}


