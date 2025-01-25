package com.sebascamayo.notesapp.features.feature_phrase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sebascamayo.notesapp.data.preferences.PhrasePreferences
import com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto.PhraseModel
import com.sebascamayo.notesapp.features.feature_phrase.domain.use_case.GetPhrase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhraseViewModel @Inject constructor(
    private val phraseUseCase: GetPhrase,
    private val phrasePreferences: PhrasePreferences
): ViewModel() {

    private val _state = MutableStateFlow(PhraseState())
    val state: StateFlow<PhraseState> = _state

    private val gson = Gson()

    init {
        loadCachedPhrase()
        fetchAndUpdatePhrase()
    }

    private fun loadCachedPhrase() {
        viewModelScope.launch {
            val cachePhraseJson = phrasePreferences.getPhrase()
            if (cachePhraseJson != null) {
                try {
                    val cachePhrase = gson.fromJson(cachePhraseJson, PhraseModel::class.java)
                    println("Cargando frase de la cache: $cachePhrase")

                    _state.value = state.value.copy(
                        phrase = cachePhrase.phrase,
                        author = cachePhrase.author
                    )
                } catch (e: Exception) {
                    println("Error al cargar frase de la cache: ${e.message}")
                }
            }
        }
    }

    private fun fetchAndUpdatePhrase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = state.value.copy(
                    isLoading = true
                )

                val remotePhrase: PhraseModel = phraseUseCase().single()

                if (state.value.phrase != remotePhrase.phrase) {
                    val jsonPhrase = Gson().toJson(remotePhrase)
                    phrasePreferences.savePhrase(jsonPhrase)

                    _state.value = state.value.copy(
                        phrase = remotePhrase.phrase,
                        author = remotePhrase.author,
                        isLoading = false
                    )
                }
                else
                {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                println("Error al obtener frase del API: ${e.message}")
                _state.value = state.value.copy(
                    isLoading = false
                )
            }
        }
    }
}