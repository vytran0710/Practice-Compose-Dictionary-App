package com.example.mypraticecomposedictionary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypraticecomposedictionary.model.Word

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedWord(word: Word)
    @Delete
    suspend fun deleteCachedWord(word: Word)

    @Query("SELECT * FROM word")
    suspend fun getAllWords(): MutableList<Word>
    @Query("SELECT * FROM word WHERE (isFavorite = 1)")
    suspend fun getAllFavoriteWords(): MutableList<Word>
}