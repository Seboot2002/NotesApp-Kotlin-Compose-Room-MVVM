package com.sebascamayo.notesapp.features.feature_notes.data.datasource.local

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "color") val color: Int,
    @PrimaryKey val id: Int? = null
){

}

class InvalidNoteException(message: String): Exception(message)