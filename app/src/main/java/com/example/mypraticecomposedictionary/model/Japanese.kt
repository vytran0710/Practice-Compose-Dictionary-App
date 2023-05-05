package com.example.mypraticecomposedictionary.model

import com.google.gson.annotations.SerializedName


data class Japanese (

  @SerializedName("word"    ) val word    : String,
  @SerializedName("reading" ) val reading : String

)