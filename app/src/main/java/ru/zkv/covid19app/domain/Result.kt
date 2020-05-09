package ru.zkv.covid19app.domain

sealed class Result<out T : Any> {
    data class Success<T : Any>(val data: T) : Result<T>()
    data class Failure(val throwable: Throwable) : Result<Nothing>()
}