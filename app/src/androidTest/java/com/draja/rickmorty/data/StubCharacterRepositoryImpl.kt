package com.draja.rickmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.network.response.LocationResponse
import com.draja.rickmorty.data.network.response.OriginResponse
import com.draja.rickmorty.data.repository.CharacterRepository

class StubCharacterRepositoryImpl : CharacterRepository {

    override suspend fun getAllCharacters(page: Int): Result<CharactersResponse> = Result.success(
        CharactersResponse(
            results = listOf(
                CharacterResponse(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "Human",
                )
            )
        )
    )

    override fun getPaginationSource(): PagingSource<Int, CharacterResponse> =
        object : PagingSource<Int, CharacterResponse>() {
            override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? =
                state.anchorPosition

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> =
                LoadResult.Page(
                    data = listOf(
                        CharacterResponse(
                            id = 1,
                            name = "Rick Sanchez",
                            status = "Alive",
                            species = "Human",
                            type = "Human",
                        )
                    ),
                    prevKey = null,
                    nextKey = null
                )
        }

    override suspend fun getCharacterById(id: String): Result<CharacterResponse> = Result.success(
        CharacterResponse(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "Human",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = LocationResponse(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            origin = OriginResponse(
                name = "Earth (C-137)",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
        )
    )
}