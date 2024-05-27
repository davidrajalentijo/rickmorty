package com.draja.rickmorty.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.launchActivity
import com.draja.rickmorty.MainActivity
import com.draja.rickmorty.di.stubCharacterDetailsModule
import com.draja.rickmorty.di.stubCharacterErrorModule
import com.draja.rickmorty.ui.characterdetail.CharacterDetailsIds
import com.draja.rickmorty.ui.characterlist.CharacterListIds
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CharacterListViewTest {

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
    fun character_list_view_is_contains_all_elements() {
        launchActivity<MainActivity>()

        composeTestRule.onAllNodes(hasTestTag(CharacterListIds.CHARACTER_LIST_ELEMENT))
            .assertCountEquals(1)
    }

    @Test
    fun character_list_click_element_go_to_details() {

        launchActivity<MainActivity>()

        composeTestRule.onNodeWithTag(CharacterListIds.CHARACTER_LIST_ELEMENT).performClick()

        composeTestRule.onNodeWithTag(CharacterDetailsIds.CHARACTER_NAME).assertIsDisplayed()
    }

    @Test
    fun character_list_view_error_view() {
        stopKoin()
        startKoin {
            modules(stubCharacterErrorModule)
        }

        launchActivity<MainActivity>()

        composeTestRule.onNodeWithTag(CharacterListIds.CHARACTER_LIST_ERROR_VIEW).assertIsDisplayed()
    }
}