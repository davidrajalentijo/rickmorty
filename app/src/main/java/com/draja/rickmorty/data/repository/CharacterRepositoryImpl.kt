package com.draja.rickmorty.data.repository

import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse

class CharacterRepositoryImpl(
    private val characterCloudDataSource: CharacterCloudDataSource
) : CharacterRepository {

    override suspend fun getAllCharacters(): Result<CharactersResponse> =
        characterCloudDataSource.getAllCharacters()

    override suspend fun getCharacterById(id: String): Result<CharacterResponse> =
        characterCloudDataSource.getCharacterById(id)
}