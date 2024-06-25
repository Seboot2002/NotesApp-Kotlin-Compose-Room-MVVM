package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository

class DeleteNote(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}