package com.draja.rickmorty.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.fakedata.charactersModel
import com.draja.rickmorty.fakedata.charactersResponse
import com.draja.rickmorty.fakedata.exception
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAllCharactersUseCaseTest {

    private val characterRepository = mockk<CharacterRepository>()
    private val getAllCharactersUseCase = GetAllCharactersUseCase(characterRepository)


    @Test
    fun `GetAllCharacters use case SUCCESS`() {
        runBlocking {
            coEvery {
                characterRepository.getAllCharacters()
            } returns Result.success(charactersResponse)


            val actual = getAllCharactersUseCase.getAllCharacters()

            assertThat(actual.getOrThrow()).equals(charactersModel)
        }
    }

    @Test
    fun `GetAllCharacters use case ERROR`() = runBlocking {
        coEvery {
            characterRepository.getAllCharacters()
        } returns Result.failure(exception)


        val actual = getAllCharactersUseCase.getAllCharacters()

        assertThat(actual.exceptionOrNull()).isEqualTo(exception)
    }
}