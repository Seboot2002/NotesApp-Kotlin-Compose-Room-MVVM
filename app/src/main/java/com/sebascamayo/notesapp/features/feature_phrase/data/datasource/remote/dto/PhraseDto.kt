package com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

// El dto es la clase en donde se almacena la data remota

data class PhraseDto (
    @SerializedName("author") val author: String,
    @SerializedName("phrase") val phrase: String
)