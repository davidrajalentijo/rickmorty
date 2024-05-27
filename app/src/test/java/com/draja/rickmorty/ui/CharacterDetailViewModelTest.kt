package com.draja.rickmorty.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.CoroutineTestRule
import com.draja.rickmorty.domain.GetCharacterByIdUseCase
import com.draja.rickmorty.fakedata.characterModel
import com.draja.rickmorty.fakedata.exception
import com.draja.rickmorty.fakedata.fakeCharacterId
import com.draja.rickmorty.ui.characterdetail.CharacterDetailsViewModel
import com.draja.ui.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getCharacterByIdUseCase = mockk<GetCharacterByIdUseCase>()

    private lateinit var viewModel: CharacterDetailsViewModel

    @Before
    fun setup() {
        viewModel = CharacterDetailsViewModel(getCharacterByIdUseCase)
    }

    @Test
    fun `Success Character Details`() = runTest {
        coEvery {
            getCharacterByIdUseCase.getCharacterById(any())
        } returns Result.success(characterModel)

        viewModel.getCharacterById(fakeCharacterId)

        assertThat(viewModel.character.value).isEqualTo(
            ViewState.Success(characterModel)
        )
    }

    @Test
    fun `Error Character Details`() = runTest {
        coEvery {
            getCharacterByIdUseCase.getCharacterById(any())
        } returns Result.failure(exception)

        viewModel.getCharacterById(fakeCharacterId)

        assertThat(viewModel.character.value).isEqualTo(
            ViewState.Error(exception)
        )
    }
}