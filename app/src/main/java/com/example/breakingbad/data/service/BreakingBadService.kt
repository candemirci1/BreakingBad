package com.example.breakingbad.data.service

import com.example.breakingbad.data.model.CharacterListDTO
import retrofit2.http.GET

interface BreakingBadService {

    @GET("characters")
    suspend fun fetchCharacters(): CharacterListDTO
}