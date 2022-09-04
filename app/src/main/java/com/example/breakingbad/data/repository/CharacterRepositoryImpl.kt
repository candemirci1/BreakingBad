package com.example.breakingbad.data.repository


import com.example.breakingbad.data.model.toCharacter
import com.example.breakingbad.data.service.BreakingBadService
import com.example.breakingbad.data.utils.Response
import com.example.breakingbad.domain.model.Character
import com.example.breakingbad.domain.repository.CharactersRepository
import retrofit2.HttpException
import java.io.IOException

class CharacterRepositoryImpl(private val service: BreakingBadService): CharactersRepository {
    override suspend fun getCharacters(): Response<List<Character>> {
        return try {
            val characters = service.fetchCharacters()
            Response.Success(characters.map { it.toCharacter() })
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }

    }

}