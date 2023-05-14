package com.example.mypraticecomposedictionary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "word")
data class Word (

  @PrimaryKey(autoGenerate = true)
  var id: Int? = null,
  @SerializedName("meta" ) val meta : Meta,
  @SerializedName("data" ) val data : MutableList<Data>,
  val isFavorite: Boolean = false

)