package com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class PhraseModel(
    @SerializedName("author") val author: String,
    @SerializedName("phrase") val phrase: String,
)