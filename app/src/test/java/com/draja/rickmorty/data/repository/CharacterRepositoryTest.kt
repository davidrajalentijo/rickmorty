package com.draja.rickmorty.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.fakedata.characterResponse
import com.draja.rickmorty.fakedata.charactersResponse
import com.draja.rickmorty.fakedata.exception
import com.draja.rickmorty.fakedata.fakeCharacterId
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterRepositoryTest {

    private val characterCloudDataSource = mockk<CharacterCloudDataSource>()
    private val characterRepositoryImpl = CharacterRepositoryImpl(characterCloudDataSource)

    @Test
    fun `getAllCharacters repository OK`() = runBlocking {
        coEvery {
            characterCloudDataSource.getAllCharacters()
        } returns Result.success(charactersResponse)

        val actual = characterRepositoryImpl.getAllCharacters()

        assertThat(actual.getOrThrow()).isEqualTo(charactersResponse)
    }

    @Test
    fun `getAllCharacters repository ERROR`() = runBlocking {
        coEvery {
            characterCloudDataSource.getAllCharacters()
        } returns Result.failure(exception)

        val actual = characterRepositoryImpl.getAllCharacters()

        assertThat(actual.exceptionOrNull()).isEqualTo(exception)
    }

    @Test
    fun `getCharacterById repository OK`() = runBlocking {
        coEvery {
            characterCloudDataSource.getCharacterById(fakeCharacterId)
        } returns Result.success(characterResponse)

        val actual = characterRepositoryImpl.getCharacterById(fakeCharacterId)

        assertThat(actual.getOrThrow()).isEqualTo(characterResponse)
    }

    @Test
    fun `getCharacterById repository ERROR`() = runBlocking {
        coEvery {
            characterCloudDataSource.getCharacterById(fakeCharacterId)
        } returns Result.failure(exception)

        val actual = characterRepositoryImpl.getCharacterById(fakeCharacterId)

        assertThat(actual.exceptionOrNull()).isEqualTo(exception)
    }
}