package com.sebascamayo.notesapp.features.feature_notes.data.mapper

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.local.NoteEntity
import com.sebascamayo.notesapp.features.feature_notes.domain.models.Note

fun NoteEntity.toModel(): Note {
    return Note(
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        color = this.color,
        id = this.id
    )
}

fun Note.toEntity(): NoteEntity{
    return NoteEntity(
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        color = this.color,
        id = this.id
    )
}