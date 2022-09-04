package com.example.breakingbad.data.service

import com.example.breakingbad.data.model.CharacterListDTO
import com.example.breakingbad.data.model.QuoteListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadService {

    @GET("characters")
    suspend fun fetchCharacters(): CharacterListDTO

    @GET("quote")
    suspend fun fetchQuotes(
        @Query("author") name: String
    ): QuoteListDTO
}