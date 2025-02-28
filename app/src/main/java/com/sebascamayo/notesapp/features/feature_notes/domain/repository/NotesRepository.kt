package com.sebascamayo.notesapp.features.feature_notes.domain.repository

import com.sebascamayo.notesapp.features.feature_notes.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository{

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}