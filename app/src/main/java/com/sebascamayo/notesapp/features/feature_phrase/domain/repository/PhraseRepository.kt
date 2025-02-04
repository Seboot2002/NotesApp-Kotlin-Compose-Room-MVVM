package com.sebascamayo.notesapp.features.feature_phrase.domain.repository

import com.sebascamayo.notesapp.features.feature_phrase.domain.models.PhraseModel
import kotlinx.coroutines.flow.Flow

interface PhraseRepository {

    fun getPhrase(): Flow<PhraseModel>
}