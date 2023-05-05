package com.example.mypraticecomposedictionary.model

import com.google.gson.annotations.SerializedName


data class Word (

  @SerializedName("meta" ) val meta : Meta,
  @SerializedName("data" ) val data : MutableList<Data>

)