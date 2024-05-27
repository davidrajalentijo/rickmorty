package com.draja.rickmorty.data

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.network.response.LocationResponse
import com.draja.rickmorty.data.network.response.OriginResponse
import com.draja.rickmorty.data.network.response.PaginationResponse
import com.draja.rickmorty.data.repository.CharacterRepository

class StubCharacterRepositoryImpl : CharacterRepository {

    override suspend fun getAllCharacters(): Result<CharactersResponse> = Result.success(
        CharactersResponse(
            info = PaginationResponse(
                count = 1,
                pages = 1,
                next = null,
                prev = null
            ),
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