package com.draja.rickmorty.domain.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.draja.rickmorty.domain.model.mapper.toModel
import com.draja.rickmorty.fakedata.characterModel
import com.draja.rickmorty.fakedata.characterResponse
import com.draja.rickmorty.fakedata.charactersModel
import com.draja.rickmorty.fakedata.charactersResponse
import org.junit.Test

class CharacterMapperTest {

    @Test
    fun `should return CharacterResponse from CharacterModel with same values`() {
        val actual = characterResponse.toModel()

        assertThat(actual).isEqualTo(characterModel)
    }

    @Test
    fun `should return CharactersResponse from CharactersModel with same values`() {
        val actual = charactersResponse.toModel()

        assertThat(actual).isEqualTo(charactersModel)
    }
}