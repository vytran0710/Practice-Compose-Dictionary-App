package com.example.mypraticecomposedictionary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypraticecomposedictionary.converter.Converters
import com.example.mypraticecomposedictionary.model.Word

@Database(
    entities = [Word::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}