package com.draja.rickmorty

sealed class Screen(val route: String) {
    data object CharactersListScreen : Screen("charactersList")
    data object CharacterDetailScreen : Screen("characterDetail/{characterId}") {
        fun createRoute(characterId: String) = "characterDetail/$characterId"
    }
}