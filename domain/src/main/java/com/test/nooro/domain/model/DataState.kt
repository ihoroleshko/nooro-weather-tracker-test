package com.test.nooro.domain.model

sealed class DataState<T> {
    class Idle<T> : DataState<T>()
    class Loading<T> : DataState<T>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val error: String) : DataState<T>()
}