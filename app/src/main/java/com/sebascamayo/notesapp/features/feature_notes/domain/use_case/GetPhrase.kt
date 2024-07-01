package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseDto
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow

class GetPhrase(
    private val repository: PhraseRepository
) {

    operator fun invoke(): Flow<PhraseModel> = repository.getPhrase()
}