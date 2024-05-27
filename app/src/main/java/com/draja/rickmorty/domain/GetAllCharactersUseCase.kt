package com.draja.rickmorty.domain

import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.domain.model.CharactersModel
import com.draja.rickmorty.domain.model.mapper.toModel

class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun getAllCharacters(page: Int = 1): Result<CharactersModel> =
        characterRepository.getAllCharacters(page).map { it.toModel() }

    fun getCharactersPaginated() = characterRepository.getPaginationSource()
}