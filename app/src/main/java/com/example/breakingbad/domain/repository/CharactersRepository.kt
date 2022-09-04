package com.example.breakingbad.domain.repository

import com.example.breakingbad.data.utils.Response
import com.example.breakingbad.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(): Response<List<Character>>
}