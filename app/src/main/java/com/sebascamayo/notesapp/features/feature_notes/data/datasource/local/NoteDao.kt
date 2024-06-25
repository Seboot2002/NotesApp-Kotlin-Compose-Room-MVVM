package com.sebascamayo.notesapp.features.feature_notes.data.datasource.local

import androidx.compose.ui.graphics.Color
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

// Declaramos las apis para la llamada al database local

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    suspend fun getNotebyId(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    /*@Query("UPDATE notes_table Set title = :title, content = :content, color = :color WHERE id = :id")
    suspend fun updateNote(id: Int?, title: String, content: String, date: String, color: Color)*/
}