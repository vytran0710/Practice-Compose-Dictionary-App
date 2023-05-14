package com.example.mypraticecomposedictionary.converter

import androidx.room.TypeConverter
import com.example.mypraticecomposedictionary.model.Data
import com.example.mypraticecomposedictionary.model.Meta
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromDataList(value: MutableList<Data>): String
    {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Data>>() {}.type
        return gson.toJson(value, type)
    }
    @TypeConverter
    fun toDataList(value: String): MutableList<Data>
    {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Data>>() {}.type
        return gson.fromJson(value, type)
    }
    @TypeConverter
    fun fromMeta(value: Meta): String
    {
        val gson = Gson()
        val type = object : TypeToken<Meta>() {}.type
        return gson.toJson(value, type)
    }
    @TypeConverter
    fun toMeta(value: String): Meta
    {
        val gson = Gson()
        val type = object : TypeToken<Meta>() {}.type
        return gson.fromJson(value, type)
    }
}