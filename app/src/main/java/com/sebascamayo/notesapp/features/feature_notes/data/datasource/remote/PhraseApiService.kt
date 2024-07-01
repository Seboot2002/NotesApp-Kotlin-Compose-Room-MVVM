package com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel
import retrofit2.http.GET
import retrofit2.Response

interface PhraseApiService {

    @GET("phrase")
    suspend fun getPhrase(): PhraseModel
}