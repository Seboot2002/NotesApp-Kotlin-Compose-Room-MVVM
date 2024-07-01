package com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class PhraseModel(
    @SerializedName("author") val author: String,
    @SerializedName("phrase") val phrase: String,
)