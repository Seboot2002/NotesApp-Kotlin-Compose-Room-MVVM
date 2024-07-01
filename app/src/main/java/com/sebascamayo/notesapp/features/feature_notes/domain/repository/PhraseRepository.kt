package com.sebascamayo.notesapp.features.feature_notes.domain.repository

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseDto
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel
import kotlinx.coroutines.flow.Flow

interface PhraseRepository {

    fun getPhrase(): Flow<PhraseModel>
}