package com.sebascamayo.notesapp.features.feature_notes.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.util.DATABASE_NAME

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)

abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

}