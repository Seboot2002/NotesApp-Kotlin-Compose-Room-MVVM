package com.sebascamayo.notesapp.features.feature_notes.presentation.notes

import com.sebascamayo.notesapp.features.feature_notes.domain.models.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.util.NoteOrder

// Esto funciona como un indice o una interfaz. Solo se va a declarar un constructor

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent() // En el dataclass es necesario un constructor
    data class DeleteNote(val note: Note): NotesEvent()
    object  RestoreNote: NotesEvent() // En un objeto no es necesario un constructor
    object  ToogleOrderSection: NotesEvent()
}