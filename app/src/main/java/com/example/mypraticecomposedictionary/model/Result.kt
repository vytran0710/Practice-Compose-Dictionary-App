package com.example.mypraticecomposedictionary.model

data class Result<T>(
    val currentState: UiState,
    val data: T?,
    val message: String?
)

enum class UiState
{
    Loading,
    Success,
    Failure,
    Idle
}