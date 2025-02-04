package com.sebascamayo.notesapp.features.feature_phrase.data.mapper

import com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto.PhraseDto
import com.sebascamayo.notesapp.features.feature_phrase.domain.models.PhraseModel

fun PhraseDto.toModel(): PhraseModel {
    return PhraseModel(
        author = this.author,
        phrase = this.phrase
    )
}

fun PhraseModel.toDto(): PhraseDto {
    return PhraseDto(
        author = this.author,
        phrase = this.phrase
    )
}