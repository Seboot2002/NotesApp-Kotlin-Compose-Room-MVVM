package com.sebascamayo.notesapp.features.feature_notes.domain.util

// Una clase sellada (sealed class) son clases que no tienen herencia. Solamente las clases u objetos declarados

sealed class OrderType {
    object Acending: OrderType() // Lo que va a retornar es OrderType.Ascending(). Los object son clases sin parametros
    object Descending: OrderType()
}