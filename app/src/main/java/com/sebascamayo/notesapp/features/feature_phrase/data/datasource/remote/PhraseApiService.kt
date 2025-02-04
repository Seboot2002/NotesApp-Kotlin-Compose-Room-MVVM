package com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote

import com.sebascamayo.notesapp.features.feature_phrase.data.datasource.remote.dto.PhraseDto
import retrofit2.http.GET

interface PhraseApiService {

    @GET("phrase")
    suspend fun getPhrase(): PhraseDto
}