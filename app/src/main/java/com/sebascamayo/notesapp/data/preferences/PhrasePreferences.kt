package com.sebascamayo.notesapp.data.preferences

import com.sebascamayo.notesapp.data.preferences.abstraction.DataStoreRepository
import kotlinx.coroutines.runBlocking

class PhrasePreferences (
    private val repository: DataStoreRepository
) {

    suspend fun savePhrase(value: String) {
        repository.putString("phrase", value)
    }

    suspend fun getPhrase(): String? {
        return repository.getString("phrase")
    }
}