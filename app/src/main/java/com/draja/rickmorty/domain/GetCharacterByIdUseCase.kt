package com.draja.rickmorty.domain

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.rickmorty.domain.model.mapper.toModel

class GetCharacterByIdUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun getCharacterById(id: String): Result<CharacterModel> =
        characterRepository.getCharacterById(id).map { it.toModel() }
}