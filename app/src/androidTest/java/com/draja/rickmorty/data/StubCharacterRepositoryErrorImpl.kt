package com.draja.rickmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.repository.CharacterRepository

class StubCharacterRepositoryErrorImpl : CharacterRepository {
    override suspend fun getAllCharacters(page: Int): Result<CharactersResponse> =
        Result.failure(Exception("Error"))

    override fun getPaginationSource(): PagingSource<Int, CharacterResponse> =
        object : PagingSource<Int, CharacterResponse>() {
            override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? =
                state.anchorPosition

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> =
                LoadResult.Error(Exception("Error"))
        }

    override suspend fun getCharacterById(id: String): Result<CharacterResponse> =
        Result.failure(Exception("Error"))
}