package com.sebascamayo.notesapp.features.feature_phrase.domain.use_case

import com.sebascamayo.notesapp.data.preferences.PhrasePreferences
import com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto.PhraseModel
import com.sebascamayo.notesapp.features.feature_phrase.domain.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import com.google.gson.Gson

class GetPhrase(
    private val phraseRepository: PhraseRepository,
    private val phrasePreferences: PhrasePreferences
) {
    private val gson = Gson()

    suspend operator fun invoke(): Flow<PhraseModel> {

        val cachePhraseJson: String? = phrasePreferences.getPhrase()
        val cachePhrase: PhraseModel? = try {
            if (cachePhraseJson != null) {
                gson.fromJson(cachePhraseJson, PhraseModel::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        println("cachePhraseJson: ${cachePhraseJson.toString()}")
        println("cachePhrase: ${cachePhrase.toString()}")

        val remotePhrase: PhraseModel = phraseRepository.getPhrase().single()
        println("remotePhrase: ${remotePhrase.toString()}")

        val newPhrase = PhraseModel(
            phrase = remotePhrase.phrase,
            author = remotePhrase.author
        )

        if (cachePhrase == null || cachePhrase.phrase != remotePhrase.phrase) {
            val jsonPhrase = gson.toJson(remotePhrase)
            phrasePreferences.savePhrase(jsonPhrase)

            return flowOf(newPhrase)
        } else {
            return flowOf(
                PhraseModel(
                    phrase = cachePhrase.phrase,
                    author = cachePhrase.author
                )
            )
        }
    }
}