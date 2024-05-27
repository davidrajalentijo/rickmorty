package com.draja.rickmorty.data.repository

import androidx.paging.PagingSource
import com.draja.rickmorty.data.CharactersPaginationSource
import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse

class CharacterRepositoryImpl(
    private val characterCloudDataSource: CharacterCloudDataSource
) : CharacterRepository {

    override suspend fun getAllCharacters(page: Int): Result<CharactersResponse> =
        characterCloudDataSource.getAllCharacters(page)

    override fun getPaginationSource(): PagingSource<Int, CharacterResponse> =
        CharactersPaginationSource(characterCloudDataSource)

    override suspend fun getCharacterById(id: String): Result<CharacterResponse> =
        characterCloudDataSource.getCharacterById(id)
}