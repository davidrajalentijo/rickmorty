package com.draja.rickmorty.data

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.repository.CharacterRepository

class StubCharacterRepositoryErrorImpl : CharacterRepository {
    override suspend fun getAllCharacters(): Result<CharactersResponse> =
        Result.failure(Exception("Error"))

    override suspend fun getCharacterById(id: String): Result<CharacterResponse> =
        Result.failure(Exception("Error"))
}