package com.sebascamayo.notesapp.features.feature_notes.data.repository

import androidx.compose.ui.graphics.Color
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.local.NoteDao
//import com.sebascamayo.notesapp.features.feature_notes.data.datasource.local.entity.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val noteDao: NoteDao
): NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNotebyId(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

}