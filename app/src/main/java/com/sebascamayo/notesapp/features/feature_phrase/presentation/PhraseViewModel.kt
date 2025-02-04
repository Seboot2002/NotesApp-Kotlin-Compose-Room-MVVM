package com.sebascamayo.notesapp.features.feature_phrase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebascamayo.notesapp.features.feature_phrase.domain.models.PhraseModel
import com.sebascamayo.notesapp.features.feature_phrase.domain.use_case.GetPhrase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhraseViewModel @Inject constructor(
    private val phraseUseCase: GetPhrase
): ViewModel() {

    private val _state = MutableStateFlow(PhraseState())
    val state: StateFlow<PhraseState> = _state

    init {
        loadCachedPhrase()
        fetchAndUpdatePhrase()
    }

    private fun loadCachedPhrase() {
        viewModelScope.launch {
            val cachePhrase: PhraseModel? = phraseUseCase.getCachePhrase()
            println("Cargando frase de la cache: $cachePhrase")

            if (cachePhrase != null) {
                _state.value = state.value.copy(
                    phrase = cachePhrase.phrase,
                    author = cachePhrase.author
                )
            }
            else
            {
                _state.value = state.value.copy(
                    isLoading = true
                )
            }

        }
    }

    private fun fetchAndUpdatePhrase() {
        viewModelScope.launch {
            try {
                val newPhrase: PhraseModel? = phraseUseCase().single()

                if (newPhrase != null && state.value.phrase != newPhrase.phrase) {
                    _state.value = state.value.copy(
                        isLoading = true
                    )

                    delay(1000)

                    _state.value = state.value.copy(
                        phrase = newPhrase.phrase,
                        author = newPhrase.author,
                        isLoading = false
                    )
                }
                else
                {
                    _state.value = state.value.copy(
                        phrase = newPhrase!!.phrase,
                        author = newPhrase!!.author,
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