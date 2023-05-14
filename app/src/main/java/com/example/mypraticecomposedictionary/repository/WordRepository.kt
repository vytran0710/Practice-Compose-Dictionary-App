package com.example.mypraticecomposedictionary.repository

import com.example.mypraticecomposedictionary.model.Word

interface WordRepository {
    // Remote
    suspend fun fetchWords(word: String): Word

    // Local
}