package com.example.mypraticecomposedictionary.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mypraticecomposedictionary.api.APIClient
import com.example.mypraticecomposedictionary.model.Result
import com.example.mypraticecomposedictionary.model.UiState
import com.example.mypraticecomposedictionary.model.Word
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _searchState =
        MutableSharedFlow<Result<Word>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val searchState = _searchState.asSharedFlow()

    fun searchWord(word: String) {
        _searchState.tryEmit(Result(UiState.Loading, null, null))

        val call = APIClient.getApi().searchWord(
            word
        )
        call.enqueue(
            object : Callback<Word> {
                override fun onResponse(
                    call: Call<Word>,
                    response: Response<Word>
                ) {
                    if (response.isSuccessful) {
                        _searchState.tryEmit(Result(UiState.Success, response.body(), null))
                    } else {
                        _searchState.tryEmit(Result(UiState.Failure, null, response.message()))
                    }
                }

                override fun onFailure(call: Call<Word>, t: Throwable) {
                    _searchState.tryEmit(Result(UiState.Failure, null, t.message))
                }

            }
        )
    }
}