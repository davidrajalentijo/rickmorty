package com.draja.rickmorty.data.repository

import androidx.paging.PagingSource
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.fakedata.characterResponse
import com.draja.rickmorty.fakedata.charactersResponse
import com.draja.rickmorty.fakedata.exception
import com.draja.rickmorty.fakedata.fakeCharacterId
import com.draja.rickmorty.fakedata.fakePage
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
            characterCloudDataSource.getAllCharacters(fakePage)
        } returns Result.success(charactersResponse)

        val actual = characterRepositoryImpl.getAllCharacters(fakePage)

        assertThat(actual.getOrThrow()).isEqualTo(charactersResponse)
    }

    @Test
    fun `getAllCharacters repository ERROR`() = runBlocking {
        coEvery {
            characterCloudDataSource.getAllCharacters(fakePage)
        } returns Result.failure(exception)

        val actual = characterRepositoryImpl.getAllCharacters(fakePage)

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

    @Test
    fun `getPaginationSource repository Success`() = runBlocking {
        coEvery {
            characterCloudDataSource.getAllCharacters(fakePage)
        } returns Result.success(charactersResponse)

        val pagingSource = characterRepositoryImpl.getPaginationSource()
        when (val actual = pagingSource.load(PagingSource.LoadParams.Refresh(1, 20, false))) {
            is PagingSource.LoadResult.Page -> {
                val data = actual.data
                assertThat(data).isEqualTo(charactersResponse.results)
            }

            is PagingSource.LoadResult.Error -> {
            }

            else -> {}
        }
    }

    @Test
    fun `getPaginationSource repository Error`() = runBlocking {
        coEvery {
            characterCloudDataSource.getAllCharacters(fakePage)
        } returns Result.failure(exception)

        val pagingSource = characterRepositoryImpl.getPaginationSource()
        when (val actual = pagingSource.load(PagingSource.LoadParams.Refresh(1, 20, false))) {
            is PagingSource.LoadResult.Page -> {
            }

            is PagingSource.LoadResult.Error -> {
                assertThat(actual.throwable.message).isEqualTo("Pagination Error")
            }

            else -> {}
        }
    }
}