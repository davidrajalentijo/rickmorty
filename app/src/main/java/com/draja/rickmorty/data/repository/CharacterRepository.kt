package com.draja.rickmorty.data.repository

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse

interface CharacterRepository {

    suspend fun getAllCharacters(): Result<CharactersResponse>

    suspend fun getCharacterById(id: String): Result<CharacterResponse>
}