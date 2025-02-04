package com.sebascamayo.notesapp.features.feature_notes.data.repository

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.local.NoteDao
import com.sebascamayo.notesapp.features.feature_notes.data.mapper.toEntity
import com.sebascamayo.notesapp.features.feature_notes.data.mapper.toModel
import com.sebascamayo.notesapp.features.feature_notes.domain.models.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val noteDao: NoteDao
): NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNotebyId(id)?.toModel()
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }

}