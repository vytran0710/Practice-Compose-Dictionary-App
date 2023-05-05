package com.example.mypraticecomposedictionary.api

import com.example.mypraticecomposedictionary.model.Word
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("search/words")
    fun searchWord(
        @Query("keyword") keyword: String
    ): Call<Word>
}