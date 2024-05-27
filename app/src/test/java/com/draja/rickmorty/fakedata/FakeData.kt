package com.draja.rickmorty.fakedata

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.network.response.PaginationResponse
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.rickmorty.domain.model.CharactersModel
import com.draja.rickmorty.domain.model.PaginationModel

val exception = Exception()

val fakeCharacterId = "1"

val charactersResponse = CharactersResponse(
    info = PaginationResponse(
        count = 1,
        pages = 1,
        next = null,
        prev = null
    ),
    results = listOf(
        CharacterResponse(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human"
        )
    )
)

val charactersModel = CharactersModel(
    info = PaginationModel(
        count = 1,
        pages = 1,
        next = null,
        prev = null
    ),
    results = listOf(
        CharacterModel(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human"
        )
    )
)

val characterResponse = CharacterResponse(
    id = 1,
    name = "Rick",
    status = "Alive",
    species = "Human"
)

val characterModel = CharacterModel(
    id = 1,
    name = "Rick",
    status = "Alive",
    species = "Human"
)