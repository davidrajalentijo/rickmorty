package com.draja.rickmorty.fakedata

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.rickmorty.domain.model.CharactersModel

val exception = Exception()

val fakeCharacterId = "1"

val fakePage = 1

val charactersResponse = CharactersResponse(
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