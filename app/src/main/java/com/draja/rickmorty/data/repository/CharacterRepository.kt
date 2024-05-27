package com.draja.rickmorty.data.repository

import androidx.paging.PagingSource
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse

interface CharacterRepository {

    suspend fun getAllCharacters(page: Int): Result<CharactersResponse>

    fun getPaginationSource(): PagingSource<Int, CharacterResponse>

    suspend fun getCharacterById(id: String): Result<CharacterResponse>
}