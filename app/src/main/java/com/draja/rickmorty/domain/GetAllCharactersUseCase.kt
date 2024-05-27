package com.draja.rickmorty.domain

import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.domain.model.CharactersModel
import com.draja.rickmorty.domain.model.mapper.toModel

class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun getAllCharacters(): Result<CharactersModel> =
        characterRepository.getAllCharacters().map { it.toModel() }
}