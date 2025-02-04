package com.sebascamayo.notesapp.features.feature_notes.domain.models

import androidx.compose.ui.graphics.Color

data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null
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