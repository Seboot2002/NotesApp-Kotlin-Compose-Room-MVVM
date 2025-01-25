package com.sebascamayo.notesapp.features.feature_notes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "color") val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object {
        val noteColors = listOf(
            Color(0xFFEE6055),
            Color(0xFF32965D),
            Color(0xFF335C81),
            Color(0xFF9B5DE5),
            Color(0xFF87255B)
        )
    }
}

class InvalidNoteException(message: String): Exception(message)