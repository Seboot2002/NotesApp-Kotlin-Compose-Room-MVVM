package com.sebascamayo.notesapp.features.feature_notes.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)


    // Esta funcion nos permite pasar un nuevo OrderType pero manteniendo el mismo NoteOrder
    fun copy(orderType: OrderType): NoteOrder {
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}