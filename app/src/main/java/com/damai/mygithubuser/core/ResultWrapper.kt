package com.damai.mygithubuser.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int, val message: String? = null) : ResultWrapper<Nothing>()
}

internal suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ResultWrapper<T?> {
    return withContext(dispatcher) {
        try {
            val call = apiCall.invoke()
            ResultWrapper.Success(call)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    ResultWrapper.GenericError(
                        code = NetworkCodes.TIMEOUT_ERROR,
                        message = ErrorCodesMapper.getMessage(NetworkCodes.CONNECTION_ERROR)
                    )
                }
                is IOException -> {
                    ResultWrapper.GenericError(
                        code = NetworkCodes.CONNECTION_ERROR,
                        message = ErrorCodesMapper.getMessage(NetworkCodes.CONNECTION_ERROR)
                    )
                }
                is HttpException -> {
                    val code = throwable.code()
                    ResultWrapper.GenericError(
                        code = code, message = throwable.localizedMessage
                    )
                }
                else -> {
                    ResultWrapper.GenericError(
                        code = NetworkCodes.GENERIC_ERROR,
                        message = throwable.localizedMessage
                    )
                }
            }
        }
    }
}
