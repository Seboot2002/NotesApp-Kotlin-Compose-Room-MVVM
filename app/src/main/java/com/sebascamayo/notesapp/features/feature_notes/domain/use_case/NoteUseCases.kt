package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

// Los use cases tiene la logica de negocio que va a utilizar el ViewModel

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)