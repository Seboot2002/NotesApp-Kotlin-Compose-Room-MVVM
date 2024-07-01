package com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote

import com.sebascamayo.notesapp.features.feature_notes.data.datasource.remote.dto.PhraseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*object RetrofitBuilder {

    private const val BASE_URL = "https://frasedeldia.azurewebsites.net/api/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}*/

/*class PhraseApiClient {

    private val retrofit = RetrofitBuilder.getRetrofit().create(PhraseService::class.java)

    suspend fun getPhrase(): PhraseModel? {

        val response = retrofit.getPhrase()
        println( response.body())
        return response.body()
    }

}*/