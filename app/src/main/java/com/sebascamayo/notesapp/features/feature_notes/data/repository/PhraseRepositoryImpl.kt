package com.sebascamayo.notesapp.features.feature_notes.data.repository

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.PhraseApiService
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseDto
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PhraseRepositoryImpl(
    private val api: PhraseApiService
): PhraseRepository {

    override fun getPhrase(): Flow<PhraseModel> {

        return flow {
            try {
                val phraseDto: PhraseModel = api.getPhrase()

                val phraseFlowData = PhraseModel(
                    phrase = phraseDto.phrase,
                    author = phraseDto.author
                )
                emit(phraseFlowData)
            } catch (e: IOException) {
                throw IOException("Error al obtener la frase: ${e.message}", e)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}