package com.sebascamayo.notesapp.features.feature_notes.presentation.phrase

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel

data class PhraseState(
    val author: String = "",
    val phrase: String = ""
)