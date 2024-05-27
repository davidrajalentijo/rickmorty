package com.draja.rickmorty.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.draja.rickmorty.di.stubCharacterDetailsModule
import com.draja.rickmorty.di.stubCharacterErrorModule
import com.draja.rickmorty.ui.characterdetail.CharacterDetailView
import com.draja.rickmorty.ui.characterdetail.CharacterDetailsIds
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CharacterDetailViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(stubCharacterDetailsModule)
        }
    }

    @Test
    fun character_detail_view_is_contains_all_elements() {
        composeTestRule.setContent {
            CharacterDetailView(characterId = "1") {}
        }

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_NAME).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_IMAGE).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_STATUS).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_SPECIES).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_TYPE).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_GENDER).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_ORIGIN).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_LOCATION).assertIsDisplayed()
    }

    @Test
    fun character_detail_view_error_view() {
        stopKoin()
        startKoin {
            modules(stubCharacterErrorModule)
        }
        composeTestRule.setContent {
            CharacterDetailView(characterId = "1") {}
        }

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_DETAILS_ERROR_VIEW).assertIsDisplayed()
    }
}