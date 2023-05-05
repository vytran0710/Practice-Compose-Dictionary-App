package com.example.mypraticecomposedictionary.model

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("slug"        ) val slug        : String,
  @SerializedName("is_common"   ) val isCommon    : Boolean,
  @SerializedName("tags"        ) val tags        : ArrayList<String>,
  @SerializedName("jlpt"        ) val jlpt        : MutableList<String>,
  @SerializedName("japanese"    ) val japanese    : MutableList<Japanese>,
  @SerializedName("senses"      ) val senses      : MutableList<Senses>,
  @SerializedName("attribution" ) val attribution : Attribution

)