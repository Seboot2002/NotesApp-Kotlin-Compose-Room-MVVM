package com.sebascamayo.notesapp.features.feature_notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebascamayo.notesapp.features.feature_notes.domain.model.Note
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.NoteUseCases
import com.sebascamayo.notesapp.features.feature_notes.domain.util.NoteOrder
import com.sebascamayo.notesapp.features.feature_notes.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

// Aqui se usa lo que se desarrolla en los use_cases

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private val _state = mutableStateOf(NotesState()) // instancia mutable
    val state: State<NotesState> = _state  //instancia inmutable y expone al _state de manera segura

    private var recentlyDeletedNote: Note? = null

    // Bloque de iniciacion al crear una instancia NotesViewModel iniciando la pantalla de notas con fecha descendiente
    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {

        when(event) {
            is NotesEvent.Order -> {

                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                    ){
                    return
                }

                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {// Lanzamos esta linea de codigo dentro del hilo
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                }
            }
            is NotesEvent.ToogleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){

        noteUseCases.getNotes(noteOrder = noteOrder).onEach { notes -> //onEach realizar acciones cada vez que Flow(flujo) emite un valor. onEach es de coroutines
            // Modificamos la instancia mutable con una copia del valor inmutable pero con los nuevos datos
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope) // launchIn es una funcion de coroutines(hilos) que lanza el Flow al viewModelScope(hilo del view model)
    }

    // hilos o coroutines son procesos que se ejecutan en paralelo al proceso principal
    //viewModelScope es el hilo/coroutine de solamente el viewModel
}