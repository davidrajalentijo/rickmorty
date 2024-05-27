package com.draja.rickmorty.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.fakedata.characterModel
import com.draja.rickmorty.fakedata.characterResponse
import com.draja.rickmorty.fakedata.exception
import com.draja.rickmorty.fakedata.fakeCharacterId
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterByIdUseCaseTest {

    private val characterRepository = mockk<CharacterRepository>()
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(characterRepository)

    @Test
    fun `GetCharacterById use case SUCCESS`() {
        runBlocking {
            coEvery {
                characterRepository.getCharacterById(fakeCharacterId)
            } returns Result.success(characterResponse)

            val actual = getCharacterByIdUseCase.getCharacterById(fakeCharacterId)

            assertThat(actual.getOrThrow()).equals(characterModel)
        }
    }

    @Test
    fun `GetCharacterById use case ERROR`() = runBlocking {
        coEvery {
            characterRepository.getCharacterById(fakeCharacterId)
        } returns Result.failure(exception)

        val actual = getCharacterByIdUseCase.getCharacterById(fakeCharacterId)

        assertThat(actual.exceptionOrNull()).isEqualTo(exception)
    }
}