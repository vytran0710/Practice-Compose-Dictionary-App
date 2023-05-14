package com.example.mypraticecomposedictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypraticecomposedictionary.api.APIClient
import com.example.mypraticecomposedictionary.model.Result
import com.example.mypraticecomposedictionary.model.UiState
import com.example.mypraticecomposedictionary.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class MainViewModel : ViewModel() {
    private val _searchState = MutableStateFlow<Result<Word>>(
        Result(UiState.Idle, null, null)
    )
    val searchState = _searchState.asStateFlow()

    fun searchWord(word: String) {
        viewModelScope.launch {
            _searchState.emit(
                Result(UiState.Loading, null, null)
            )

            val response = withContext(Dispatchers.IO)
            {
                APIClient.getApi().searchWord(
                    word
                )
            }

            _searchState.emit(Result(UiState.Success, response, null))
        }
    }
}