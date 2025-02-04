package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

import com.sebascamayo.notesapp.features.feature_notes.domain.models.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository

class GetNote(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Int): Note? {

        return repository.getNoteById(id)
    }
}