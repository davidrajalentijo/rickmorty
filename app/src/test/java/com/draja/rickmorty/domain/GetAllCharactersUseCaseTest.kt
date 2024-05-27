package com.draja.rickmorty.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.fakedata.charactersModel
import com.draja.rickmorty.fakedata.charactersResponse
import com.draja.rickmorty.fakedata.exception
import com.draja.rickmorty.fakedata.fakePage
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
                characterRepository.getAllCharacters(fakePage)
            } returns Result.success(charactersResponse)

            val actual = getAllCharactersUseCase.getAllCharacters()

            assertThat(actual.getOrThrow()).equals(charactersModel)
        }
    }

    @Test
    fun `GetAllCharacters use case ERROR`() = runBlocking {
        coEvery {
            characterRepository.getAllCharacters(fakePage)
        } returns Result.failure(exception)

        val actual = getAllCharactersUseCase.getAllCharacters()

        assertThat(actual.exceptionOrNull()).isEqualTo(exception)
    }

    @Test
    fun `getCharactersPaginated use case SUCCESS`() = runBlocking {
        coEvery {
            characterRepository.getPaginationSource()
        } returns object : PagingSource<Int, CharacterResponse>() {
            override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? = null

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> =
                LoadResult.Page(
                    data = charactersResponse.results,
                    prevKey = null,
                    nextKey = fakePage + 1
                )
        }

        val pagingSource = getAllCharactersUseCase.getCharactersPaginated()

        when (val actual = pagingSource.load(PagingSource.LoadParams.Refresh(1, 20, false))) {
            is PagingSource.LoadResult.Page -> {
                assertThat(actual.data).isEqualTo(charactersResponse.results)
            }

            is PagingSource.LoadResult.Error -> {
            }

            else -> {}
        }
    }

    @Test
    fun `getCharactersPaginated use case ERROR`() = runBlocking {
        coEvery {
            characterRepository.getPaginationSource()
        } returns object : PagingSource<Int, CharacterResponse>() {
            override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? = null

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> =
                LoadResult.Error(exception)
        }

        val pagingSource = getAllCharactersUseCase.getCharactersPaginated()

        when (val actual = pagingSource.load(PagingSource.LoadParams.Refresh(1, 20, false))) {
            is PagingSource.LoadResult.Page -> {}

            is PagingSource.LoadResult.Error -> {
                assertThat(actual.throwable.message).isEqualTo(null)
            }

            else -> {}
        }
    }
}
