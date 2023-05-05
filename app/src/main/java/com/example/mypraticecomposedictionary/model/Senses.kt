package com.example.mypraticecomposedictionary.model

import com.google.gson.annotations.SerializedName


data class Senses (

  @SerializedName("english_definitions" ) val englishDefinitions : MutableList<String>,
  @SerializedName("parts_of_speech"     ) val partsOfSpeech      : MutableList<String>,
  @SerializedName("links"               ) val links              : MutableList<Links>,
  @SerializedName("tags"                ) val tags               : MutableList<String>,
  @SerializedName("restrictions"        ) val restrictions       : MutableList<String>,
  @SerializedName("see_also"            ) val seeAlso            : MutableList<String>,
  @SerializedName("antonyms"            ) val antonyms           : MutableList<String>,
  @SerializedName("source"              ) val source             : MutableList<String>,
  @SerializedName("info"                ) val info               : MutableList<String>

)