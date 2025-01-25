package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

import com.sebascamayo.notesapp.features.feature_notes.domain.model.InvalidNoteException
import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository

class AddNote(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: Note) {

        if(note.title.isBlank()){
            throw InvalidNoteException("The title of note can't be empty")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of note can't be empty")
        }
        repository.insertNote(note)
    }
}