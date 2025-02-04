package com.sebascamayo.notesapp.features.feature_phrase.domain.use_case

import com.sebascamayo.notesapp.data.preferences.PhrasePreferences
import com.sebascamayo.notesapp.features.feature_phrase.domain.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import com.sebascamayo.notesapp.features.feature_phrase.domain.models.PhraseModel

class GetPhrase(
    private val phraseRepository: PhraseRepository,
    private val phrasePreferences: PhrasePreferences
) {

    suspend operator fun invoke(): Flow<PhraseModel?> {

        val remotePhrase: PhraseModel? = getRemotePhrase()
        
        val cachePhrase: PhraseModel? = getCachePhrase()

        if (cachePhrase!!.phrase != remotePhrase!!.phrase) {
            return flowOf(remotePhrase)
        } else {
            return flowOf(
                PhraseModel(
                    phrase = cachePhrase.phrase,
                    author = cachePhrase.author
                )
            )
        }
    }

    suspend fun getCachePhrase(): PhraseModel? {
        val cachePhraseJson: String? = phrasePreferences.getPhrase()
        val cachePhrase: PhraseModel? = try {
            if (cachePhraseJson != null) {
                phrasePreferences.phraseFromJson(cachePhraseJson)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

        println("cachePhraseJson: ${cachePhraseJson.toString()}")
        println("cachePhrase: ${cachePhrase.toString()}")

        return cachePhrase
    }

    suspend fun getRemotePhrase(): PhraseModel? {
        val remotePhrase: PhraseModel = phraseRepository.getPhrase().single()
        println("remotePhrase: ${remotePhrase}")

        val jsonPhrase = phrasePreferences.phraseToJson(remotePhrase)

        phrasePreferences.savePhrase(jsonPhrase)

        return remotePhrase
    }
}