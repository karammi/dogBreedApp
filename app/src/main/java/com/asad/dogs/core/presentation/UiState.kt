package com.asad.dogs.core.presentation

sealed class UiState<out T, out R>(open val data: T? = null, open val message: R? = null) {
    data class Success<T>(override val data: T) : UiState<T, Nothing>(data = data)
    data class Error<E>(override val message: E?) : UiState<Nothing, E>(message = message)
    object Loading : UiState<Nothing, Nothing>()
    object Empty : UiState<Nothing, Nothing>()
}
