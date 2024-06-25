package com.sebascamayo.notesapp.features.feature_notes.domain.use_case

import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository
import com.sebascamayo.notesapp.features.feature_notes.domain.util.NoteOrder
import com.sebascamayo.notesapp.features.feature_notes.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NotesRepository
) {

    //El use case solo debe tener una funcion que ueda ser llamada desde afuera
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending) // Declaramos el NoteOrder por defecto
    ): Flow<List<Note>> {

        return repository.getNotes().map { notes ->

            when(noteOrder.orderType) {
                is OrderType.Acending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}