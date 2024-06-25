package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNote(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Int): Note? {

        return repository.getNoteById(id)
    }
}