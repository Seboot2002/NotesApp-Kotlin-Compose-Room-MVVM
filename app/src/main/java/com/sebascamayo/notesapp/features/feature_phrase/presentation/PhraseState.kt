package com.sebascamayo.notesapp.features.feature_phrase.presentation

data class PhraseState(
    val author: String = "",
    val phrase: String = "",
    val isLoading: Boolean = false
)