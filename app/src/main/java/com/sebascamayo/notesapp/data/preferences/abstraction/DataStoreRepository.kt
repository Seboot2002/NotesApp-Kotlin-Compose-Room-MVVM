package com.sebascamayo.notesapp.data.preferences.abstraction

interface DataStoreRepository {

    suspend fun putString(key: String, value: String)
    suspend fun putInt(key: String, value: Int)

    suspend fun getString(key: String): String?
    suspend fun getInt(key: String, value: String): Int?
}