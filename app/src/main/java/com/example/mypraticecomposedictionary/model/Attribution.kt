package com.example.mypraticecomposedictionary.model

import com.google.gson.annotations.SerializedName


data class Attribution (

  @SerializedName("jmdict"   ) val jmdict   : Boolean,
  @SerializedName("jmnedict" ) val jmnedict : Boolean,
  @SerializedName("dbpedia"  ) val dbpedia  : String

)