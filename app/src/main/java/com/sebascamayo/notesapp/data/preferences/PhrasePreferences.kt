package com.sebascamayo.notesapp.data.preferences

import com.google.gson.Gson
import com.sebascamayo.notesapp.data.preferences.abstraction.DataStoreRepository
import com.sebascamayo.notesapp.features.feature_phrase.domain.models.PhraseModel
import kotlinx.coroutines.runBlocking

class PhrasePreferences (
    private val repository: DataStoreRepository
) {
    private val gson = Gson()

    suspend fun savePhrase(value: String) {
        repository.putString("phrase", value)
    }

    suspend fun getPhrase(): String? {
        return repository.getString("phrase")
    }

    fun phraseToJson(phraseModel: PhraseModel): String {
        return gson.toJson(phraseModel)
    }

    fun phraseFromJson(phraseModelJson: String): PhraseModel {
        return gson.fromJson(phraseModelJson, PhraseModel::class.java)
    }
}