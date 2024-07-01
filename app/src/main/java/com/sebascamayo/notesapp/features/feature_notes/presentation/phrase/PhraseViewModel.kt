package com.sebascamayo.notesapp.features.feature_notes.presentation.phrase

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.PhraseApiService
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.GetPhrase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PhraseViewModel @Inject constructor(
    private val phraseUseCase: GetPhrase
): ViewModel() {

    private val _state = MutableStateFlow(PhraseState())
    val state: StateFlow<PhraseState> =_state

    init {
        phraseUseCase()
        onEvent()
    }

    fun onEvent() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                getPhrase()
            }
        }
    }

    private suspend fun getPhrase () {
        try {
            val response: PhraseModel = phraseUseCase().single()

            println("GANAMOS, tenemos la data: $response")

            _state.value = state.value.copy(
                phrase = response.phrase,
                author = response.author
            )


        } catch (e: Exception) {

            println("HAY UN ERROR")
        }
    }
}