package com.sebascamayo.notesapp.features.feature_phrase.data.repository

import com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.PhraseApiService
import com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto.PhraseDto
import com.sebascamayo.notesapp.features.feature_phrase.data.mapper.toModel
import com.sebascamayo.notesapp.features.feature_phrase.domain.models.PhraseModel
import com.sebascamayo.notesapp.features.feature_phrase.domain.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PhraseRepositoryImpl(
    private val api: PhraseApiService
): PhraseRepository {

    override fun getPhrase(): Flow<PhraseModel> {

        return flow {
            try {
                val phraseDto: PhraseDto = api.getPhrase()

                val phraseFlowData = phraseDto.toModel()
                emit(phraseFlowData)
            } catch (e: IOException) {
                throw IOException("Error al obtener la frase: ${e.message}", e)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}